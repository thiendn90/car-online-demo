/**
 * Created by ThienDN on 9/2/2015.
 */

// PROCEDURE METHODS (return nothing)
Node.prototype.on = function (eventName, eventHandler) {
    this.addEventListener(eventName, eventHandler);
};
Node.prototype.off = function (eventName, eventHandler) {
    this.removeEventListener(eventName, eventHandler);
};
Node.prototype.fadeIn = function (duration) {
    var self = this;
    if (!duration) {
        duration = 400;
    }
    this.style.opacity = 0;
    var last = +new Date();
    var tick = function () {
        self.style.opacity = +self.style.opacity + (new Date() - last) / duration;
        last = +new Date();

        if (+self.style.opacity < 1) {
            if (window.requestAnimationFrame) {
                window.requestAnimationFrame(tick);
            } else {
                setTimeout(tick, 16);
            }
        }
    };

    tick();
};
Node.prototype.hide = function () {
    this.style.display = 'none';
};
Node.prototype.show = function () {
    this.style.display = '';
};
Node.prototype.addClass = function (className) {
    if (this.classList) {
        this.classList.add(className);
    }
    else {
        this.className += ' ' + className;
    }
};
Node.prototype.removeClass = function (className) {
    if (this.classList) {
        this.classList.remove(className);
    } else {
        this.className = this.className.replace(new RegExp('(^|\\b)' + className.split(' ').join('|') + '(\\b|$)', 'gi'), ' ');
    }
};
Node.prototype.trigger = function (eventName, data) {
    var event;
    if (data) {
        if (window.CustomEvent) {
            event = new window.CustomEvent(eventName, {detail: data});
        } else {
            event = window.document.createEvent('CustomEvent');
            event.initCustomEvent(eventName, true, true, data);
        }

        this.dispatchEvent(event);
    } else {
        event = window.document.createEvent('HTMLEvents');
        event.initEvent(eventName, true, false);
        this.dispatchEvent(event);
    }
};

// FUNCTION METHODS (return thing)
Node.prototype.find = function (selector) {
    return this.querySelectorAll(selector);
};
Node.prototype.parent = function () {
    return this.parentNode;
};
Node.prototype.css = function (ruleName) {
    return window.getComputedStyle(this)[ruleName];
};
Node.prototype.attr = function (attrName) {
    return this.getAttribute(attrName);
};
Node.prototype.html = function (value) {
    if (value) {
        this.innerHTML = value;
    } else {
        return this.innerHTML;
    }
};
Node.prototype.text = function (value) {
    if (value) {
        this.textContent = value;
    } else {
        return this.textContent;
    }
};
Node.prototype.hasClass = function (className) {
    if (this.classList) {
        return this.classList.contains(className);
    } else {
        return new RegExp('(^| )' + className + '( |$)', 'gi').test(this.className);
    }
};
Node.prototype.offset =  function () {
    var rect = this.getBoundingClientRect();
    return {
        top: rect.top + window.document.body.scrollTop,
        left: rect.left + window.document.body.scrollLeft
    };
};

/**
 * Created by TrungDQ3 on 8/13/14.
 */

/**
 * Normal slider with thumbnails at the bottom.
 * TODO: for now it is only display 6 thumbnails. Need more work to make it flexible
 * @param id
 * @constructor
 */
function Slider(id) {
    this.node = document.getElementById(id);
    this.items = this.node.find('br');
    this.node.innerHTML = ''; // Or show loading visual effect

    this.buildBanner();
    this.buildThumbnailBar();

    this._calcSizes();

    this.startSlider();

    this._bindWindowResize();
}

Slider.prototype.IMAGE_HEIGHT = 266;
Slider.prototype.IMAGE_WIDTH = 560;
Slider.prototype.THUMBNAIL_HEIGHT = 53;
Slider.prototype.THUMBNAIL_WIDTH = 85;

Slider.prototype.sliderBannerHeight = 0;
Slider.prototype.sliderHeight = 0;
Slider.prototype.sliderThumbnailBarHeight = 60;
Slider.prototype.sliderPadding = 10;
Slider.prototype.thumbnailNumber = 6;
Slider.prototype.interval = 5000;

Slider.prototype.node = null;
Slider.prototype.banner = null;
Slider.prototype.roller = null;
Slider.prototype.thumbnailBar = null;

Slider.prototype.items = [];
Slider.prototype._sliderWidth = 0;
Slider.prototype._thumbnailNodes = [];
Slider.prototype._currentIndex = 0;
Slider.prototype._timer = 0;

Slider.prototype._bindWindowResize = function () {
    var self = this;
    window.addEventListener('resize', function (e) {
        self._calcSizes();
    });
};

Slider.prototype._calcSizes = function () {

    this.sliderBannerHeight = this.banner.offsetWidth * this.IMAGE_HEIGHT / this.IMAGE_WIDTH;

    this.sliderThumbnailBarHeight = ((this._sliderWidth - 10 *
    (this.thumbnailNumber - 1)) / this.thumbnailNumber) *
    this.THUMBNAIL_HEIGHT / this.THUMBNAIL_WIDTH + 10 * 2;

    this.sliderHeight = this.sliderBannerHeight + this.sliderThumbnailBarHeight + this.sliderPadding * 2;

    this.node.style.height = this.sliderHeight + 'px';
    this.node.style.padding = this.sliderPadding + 'px';
    this.banner.style.height = this.sliderBannerHeight + 'px';
    this.roller.style.height = this.sliderBannerHeight + 'px';
    this._sliderWidth = this.banner.offsetWidth;
    var images = this.roller.find('.image');
    for (var i = 0; i < images.length; i++) {
        images[i].style.height = this.sliderBannerHeight + 'px';
        images[i].style.width = this._sliderWidth + 'px';
    }

    this.thumbnailBar.style.height = this.sliderThumbnailBarHeight + 'px';
    this.thumbnailBar.style.paddingTop = this.sliderPadding + 'px';

    var thumbnails = this.thumbnailBar.find('.thumbnail');
    for (var i = 0; i < thumbnails.length; i++) {
        thumbnails[i].style.height = (this.sliderThumbnailBarHeight - this.sliderPadding) + 'px';
        thumbnails[i].style.width = (this._sliderWidth - 10 * (this.thumbnailNumber - 1)) / this.thumbnailNumber + 'px';
    }
};

Slider.prototype.startSlider = function () {
    var self = this;
    this._setActive();
    this.node.on('click', function (e) {
        if (e.target.hasClass('thumbnail')) {
            self._currentIndex = +e.target.dataset.id;
            self._setActive();
        }
    });
    this._startTimer();
};

Slider.prototype._startTimer = function () {
    var self = this;
    clearTimeout(this._timer);
    this._timer = setTimeout(function () {
        self._currentIndex++;
        if (self._currentIndex >= self.thumbnailNumber) {
            self._currentIndex = 0;
        }
        self._setActive();
    }, this.interval);
};

Slider.prototype._setActive = function (index) {
    if (!index) index = this._currentIndex;
    for (var i = 0; i < this._thumbnailNodes.length; i++) {
        this._thumbnailNodes[i].removeClass('active');
    }
    this._thumbnailNodes[index].addClass('active');

    this._setPosition();

    this._startTimer();
};

Slider.prototype._setPosition = function (index) {
    if (!index) index = this._currentIndex;
    this.roller.style.transform = 'translate3d(' + (-index * this.banner.offsetWidth) + 'px, 0, 0)';
};

Slider.prototype.buildBanner = function () {
    this.banner = document.createElement('div');
    this.banner.addClass('slider-banner');
    this.node.style.height = this.sliderHeight + 'px';
    this.node.style.padding = this.sliderPadding + 'px';
    this.node.appendChild(this.banner);
    this._sliderWidth = this.banner.offsetWidth;
    this.roller = document.createElement('div');
    this.roller.addClass('slider-roller');
    this.roller.style.width = this.items.length * this._sliderWidth + 'px';
    for (var i = 0; i < this.items.length; i++) {
        var img = document.createElement('div');
        img.addClass('image');
        img.style.width = this._sliderWidth + 'px';
        img.style.backgroundImage = 'url("' + this.items[i].dataset.image + '")';
        this.roller.appendChild(img);
    }
    this.banner.appendChild(this.roller);
};

Slider.prototype.buildThumbnailBar = function () {
    this.thumbnailBar = document.createElement('div');
    this.thumbnailBar.addClass('thumbnail-bar');
    for (var i = 0; i < this.items.length; i++) {
        var img = document.createElement('div');
        img.addClass('thumbnail');
        img.dataset.id = i;
        img.style.backgroundImage = 'url("' + this.items[i].dataset.thumbnail + '")';
        this._thumbnailNodes.push(img);
        this.thumbnailBar.appendChild(img);
    }
    this.node.appendChild(this.thumbnailBar);
};

/**
 * Created by trungdq on 12/07/2014.
 */

var Popup = function (targetId, width, height) {
    var self = this;
    this.node = document.createElement('div');
    this.node.id = targetId;
    this.node.addClass('popup');
    this.node.innerHTML = '<div class="popup-content"><div class="close-btn" id="close-btn">×</div>' + document.getElementById(targetId).html() + '</div>';
    var content = this.node.querySelector('.popup-content');
    content.style.width = width ? width + 'px' : '';
    content.style.height = height ? height + 'px': '';
    this.node.on('click', function (e) {
        if (e.target.id == 'close-btn' || e.target.id == targetId) {
            self.close();
        }
    });

};

Popup.prototype.show = function () {
    document.body.appendChild(this.node);
};

Popup.prototype.close = function () {
    this.node.remove();
};

function openPopup() {
    new Popup('welcome-popup', 500, 300).show();
}

var slider;
document.on('DOMContentLoaded', function () {
    if (document.getElementById('slider')) {
        slider = new Slider('slider');
    }
});
