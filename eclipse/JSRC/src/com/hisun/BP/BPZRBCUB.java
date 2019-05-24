package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRBCUB {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTBCUS_BR = new brParm();
    DBParm BPTBCUS_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRBCUB";
    String K_TBL_BCUS = "BPTBCUS ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRBCUS BPRBCUS = new BPRBCUS();
    SCCGWA SCCGWA;
    BPCRBCUB BPCRBCUB;
    BPRBCUS BPRBCUS1;
    public void MP(SCCGWA SCCGWA, BPCRBCUB BPCRBCUB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRBCUB = BPCRBCUB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRBCUB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRBCUS1 = (BPRBCUS) BPCRBCUB.INFO.POINTER;
        IBS.init(SCCGWA, BPRBCUS);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBCUS1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBCUS1, BPRBCUS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRBCUB.INFO.FUNC == '1'
            || BPCRBCUB.INFO.FUNC == '2') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBCUB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBCUB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBCUB.INFO.FUNC == 'W') {
            B040_REWRITE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRBCUB.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRBCUB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBCUS);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBCUS, BPRBCUS1);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRBCUB.INFO.FUNC == '1') {
            T000_STARTBR_BPTBCUS_01();
            if (pgmRtn) return;
        } else if (BPCRBCUB.INFO.FUNC == '2') {
            T000_STARTBR_BPTBCUS_02();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTBCUS();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTBCUS();
        if (pgmRtn) return;
    }
    public void B040_REWRITE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTBCUS();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTBCUS_01() throws IOException,SQLException,Exception {
        BPTBCUS_BR.rp = new DBParm();
        BPTBCUS_BR.rp.TableName = "BPTBCUS";
        BPTBCUS_BR.rp.where = "BEG_NO <= :BPRBCUS.KEY.BEG_NO "
            + "AND END_NO >= :BPRBCUS.KEY.END_NO "
            + "AND AC = :BPRBCUS.KEY.AC "
            + "AND BV_CODE = :BPRBCUS.KEY.BV_CODE";
        BPTBCUS_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRBCUS, this, BPTBCUS_BR);
    }
    public void T000_STARTBR_BPTBCUS_02() throws IOException,SQLException,Exception {
        BPTBCUS_BR.rp = new DBParm();
        BPTBCUS_BR.rp.TableName = "BPTBCUS";
        BPTBCUS_BR.rp.where = "AC = :BPRBCUS.KEY.AC";
        BPTBCUS_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRBCUS, this, BPTBCUS_BR);
    }
    public void T000_READNEXT_BPTBCUS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRBCUS, this, BPTBCUS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBCUB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBCUB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTBCUS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTBCUS_BR);
    }
    public void T000_REWRITE_BPTBCUS() throws IOException,SQLException,Exception {
        BPTBCUS_RD = new DBParm();
        BPTBCUS_RD.TableName = "BPTBCUS";
        IBS.REWRITE(SCCGWA, BPRBCUS, BPTBCUS_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRBCUB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRBCUB = ");
            CEP.TRC(SCCGWA, BPCRBCUB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
