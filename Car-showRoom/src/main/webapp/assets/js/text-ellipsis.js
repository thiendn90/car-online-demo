/**
 * Created by thiendn on 7/12/2015.
 * ellipsis util.
 * Must be including Jquery before this script
 */

//String utils
var $strings = $strings || {};

$strings.ellipsis = (function(autoEllipsis) {

    /*
     * tNode: text node
     * eNode: ellipsis node
     */
    function _ellipsisElement(tNode, suffix, lineClamp, eNode) {

        suffix = suffix ? suffix : "...";

        /*
         * Get max height of div including margin, padding and border
         * TODO: remove using Jquery to adapt TV4 and TV5
         */
        var fontSize = $(eNode).css('font-size');
        var lineHeight = parseFloat($(eNode).css('line-height').replace('px', ''));
        isNaN(lineHeight) && (lineHeight = Math.ceil(parseFloat(fontSize.replace('px', '')) * 1.5));
        var paddingMarginBorderWidth = $(eNode).outerHeight(true) - $(eNode).height();
        var maxHeight = Math.ceil(lineHeight * lineClamp + paddingMarginBorderWidth);
        if (maxHeight == 0) return;
        eNode.style.maxHeight = maxHeight+"px";

        var orgStr;

        orgStr = tNode.textContent.trim();

        var afterCutLength = orgStr.length;
        var minLength = 0;

        var doEllipsis = eNode.scrollHeight > maxHeight ? true : false;
        if(doEllipsis){
            tNode.textContent = tNode.textContent + suffix;
        }else{
            tNode.textContent = orgStr;
        }
        var checkLength = function(){
            if(eNode.scrollHeight >= eNode.offsetHeight){
                return eNode.scrollHeight <= maxHeight;
            }else{
                return true;
            }
        }
        var count=0;

        //First check height of node. DO NOT repeat doEllipsis by eNode.scrollHeight > maxHeight
        while (doEllipsis) {
            count++;
            if (!checkLength()) {
                tNode.textContent = tNode.textContent.substring(0, tNode.textContent.length - suffix.length);

                orgStr = tNode.textContent;

                if (orgStr == "") return;
            } else {
                tNode.textContent = tNode.textContent.substring(0, tNode.textContent.length - suffix.length);

                if (afterCutLength - minLength <= 1) {
                    doEllipsis = false;
                    tNode.textContent = tNode.textContent + suffix;
                    break;
                }
                minLength = afterCutLength;
            }

            afterCutLength = (orgStr.length + minLength) / 2;
            //Add suffix to check height and remove when checking finished.
            tNode.textContent = orgStr.substring(0, afterCutLength) + suffix;
            if(count == 20){
                return;
            }
        }
    }

    /*
     * Get all text inside ellipsis node
     */
    var _getTextNodes = function (node) {
        var tnList = []; //Text node list
        if (!node || !node.childNodes || node.childNodes.length == 0) return [];
        for (var i = 0; i < node.childNodes.length; i++) {
            var child = node.childNodes[i];
            if (child instanceof Text && child.textContent.trim() != "") tnList.push(child);
            else {
                var list = _getTextNodes(child);
                tnList = tnList.concat(list);
            }
        }
        return tnList;
    }


    function _ellipsis(){

        var configs = arguments;

        for(var i = 0; i< configs.length ; i++){
            var config = configs[i];

            var listEllipsis = [];
            if(!config.selector && config.node) listEllipsis = [config.node];
            else listEllipsis = document.querySelectorAll(config.selector);

            for (var n = 0; n < listEllipsis.length; n++) {
                var ellipsisNode = listEllipsis[n];

                var textNodes = _getTextNodes(ellipsisNode) || [];

                for (var j = textNodes.length - 1; j >= 0; j--) {
                    _ellipsisElement(textNodes[j], config.suffix, config.lineClamp, ellipsisNode);
                }
            }
        }
    }

    /*
     * Default configs for ellipsis
     * selector: selector
     * line clamp: number line you want to sub string. Same as -webkit-line-clamp
     * suffix: suffix you want to add to end of line when it too long
     * node: if you want to pass element instead of selector, you can pass here
     */

    var _configs = [{
        selector: ".line-clamp-1",
        lineClamp :1,
        suffix: "..."
    },{
        selector: ".line-clamp-2",
        lineClamp :2,
        suffix: "..."
    },{
        selector: ".line-clamp-3",
        lineClamp :3,
        suffix: "..."
    },{
        selector: ".line-clamp-4",
        lineClamp :4,
        suffix: "..."
    }]

    var timeoutId = 0;
    var _checkAll = function(runByUser,timeout){
        if(!runByUser && $strings.ellipsis.automation === false) return;

        //Run on new thread to prevent error when execute
        timeoutId == 0 && (timeoutId = setTimeout(function(){
            _ellipsis.apply(undefined, _configs);
            timeoutId = 0;
        },(timeout?timeout:0)));
    }

    var _config = function(config){
        if(config.selector || config.node) _configs.push(config);;
    }

    /**
     * We can not use document(window) ready because it fires our code when DOM is ready except images.
     * Some span stand with some images will make wrong cutting.
     */
    if(autoEllipsis){
        if(window.addEventListener){
            window.addEventListener("load",_checkAll.bind(undefined, false ,0),false);
        }else{
            window.attachEvent && window.attachEvent("onload",_checkAll.bind(undefined, false ,0));
        }
    }

    return {
        check : _ellipsis,
        checkAll : _checkAll.bind(undefined,true),
        config : _config
    };
})(true);