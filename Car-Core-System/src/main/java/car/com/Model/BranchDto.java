package car.com.Model;

import javax.persistence.*;

/**
 * Created by ThienDN on 9/2/2015.
 */
@Entity
@Table(name = "branch_table")
public class BranchDto {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int branchId;
    @Column
    private String branchCode;
    @Column
    private String branchLogo;
    @Column
    private String branchUrl;
    @Column
    private String BranchDescription;

    public String getBranchDescription() {
        return BranchDescription;
    }

    public void setBranchDescription(String branchDescription) {
        BranchDescription = branchDescription;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchLogo() {
        return branchLogo;
    }

    public void setBranchLogo(String branchLogo) {
        this.branchLogo = branchLogo;
    }

    public String getBranchUrl() {
        return branchUrl;
    }

    public void setBranchUrl(String branchUrl) {
        this.branchUrl = branchUrl;
    }

    public BranchDto(){}

    public BranchDto(int branchId,String branchCode, String branchLogo, String branchUrl,String branchDescription) {
        super();
        this.branchId = branchId;
        this.branchCode = branchCode;
        this.branchLogo = branchLogo;
        this.branchUrl = branchUrl;
        this.BranchDescription = branchDescription;
    }
}
