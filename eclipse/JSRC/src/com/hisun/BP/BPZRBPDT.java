package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRBPDT {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTPDTP_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRBPDT";
    String K_TBL_BCUS = "BPTPDTP ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRPDTP BPRPDTP = new BPRPDTP();
    SCCGWA SCCGWA;
    BPCRBPDT BPCRBPDT;
    BPRPDTP BPRPDTP1;
    public void MP(SCCGWA SCCGWA, BPCRBPDT BPCRBPDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRBPDT = BPCRBPDT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRBPDT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRPDTP1 = (BPRPDTP) BPCRBPDT.INFO.POINTER;
        IBS.init(SCCGWA, BPRPDTP);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPDTP1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRPDTP1, BPRPDTP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRBPDT.INFO.FUNC == 'S') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBPDT.INFO.FUNC == 'R') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBPDT.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRBPDT.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRBPDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPDTP);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRPDTP, BPRPDTP1);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRBPDT.REQID == 1) {
            T000_STARTBR_BPTPDTP_01();
            if (pgmRtn) return;
        } else if (BPCRBPDT.REQID == 2) {
            T000_STARTBR_BPTPDTP_02();
            if (pgmRtn) return;
        } else if (BPCRBPDT.REQID == 3) {
            T000_STARTBR_BPTPDTP_03();
            if (pgmRtn) return;
        } else if (BPCRBPDT.REQID == 4) {
            T000_STARTBR_BPTPDTP_04();
            if (pgmRtn) return;
        } else if (BPCRBPDT.REQID == 5) {
            T000_STARTBR_BPTPDTP_05();
            if (pgmRtn) return;
        } else if (BPCRBPDT.REQID == 6) {
            T000_STARTBR_BPTPDTP_06();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRBPDT.REQID == 1) {
            T000_READNEXT_BPTPDTP_1();
            if (pgmRtn) return;
        } else if (BPCRBPDT.REQID == 2) {
            T000_READNEXT_BPTPDTP_2();
            if (pgmRtn) return;
        } else if (BPCRBPDT.REQID == 3) {
            T000_READNEXT_BPTPDTP_3();
            if (pgmRtn) return;
        } else if (BPCRBPDT.REQID == 4) {
            T000_READNEXT_BPTPDTP_4();
            if (pgmRtn) return;
        } else if (BPCRBPDT.REQID == 5) {
            T000_READNEXT_BPTPDTP_5();
            if (pgmRtn) return;
        } else if (BPCRBPDT.REQID == 6) {
            T000_READNEXT_BPTPDTP_6();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRBPDT.REQID == 1) {
            T000_ENDBR_BPTPDTP_1();
            if (pgmRtn) return;
        } else if (BPCRBPDT.REQID == 2) {
            T000_ENDBR_BPTPDTP_2();
            if (pgmRtn) return;
        } else if (BPCRBPDT.REQID == 3) {
            T000_ENDBR_BPTPDTP_3();
            if (pgmRtn) return;
        } else if (BPCRBPDT.REQID == 4) {
            T000_ENDBR_BPTPDTP_4();
            if (pgmRtn) return;
        } else if (BPCRBPDT.REQID == 5) {
            T000_ENDBR_BPTPDTP_5();
            if (pgmRtn) return;
        } else if (BPCRBPDT.REQID == 6) {
            T000_ENDBR_BPTPDTP_6();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_STARTBR_BPTPDTP_01() throws IOException,SQLException,Exception {
        BPTPDTP_BR.rp = new DBParm();
        BPTPDTP_BR.rp.TableName = "BPTPDTP";
        BPTPDTP_BR.rp.Reqid = 1;
        IBS.STARTBR(SCCGWA, BPRPDTP, BPTPDTP_BR);
    }
    public void T000_STARTBR_BPTPDTP_02() throws IOException,SQLException,Exception {
        BPTPDTP_BR.rp = new DBParm();
        BPTPDTP_BR.rp.TableName = "BPTPDTP";
        BPTPDTP_BR.rp.where = "SUPR_TYPE = :BPRPDTP.KEY.PRDT_TYPE";
        BPTPDTP_BR.rp.Reqid = 2;
        IBS.STARTBR(SCCGWA, BPRPDTP, this, BPTPDTP_BR);
    }
    public void T000_STARTBR_BPTPDTP_03() throws IOException,SQLException,Exception {
        BPTPDTP_BR.rp = new DBParm();
        BPTPDTP_BR.rp.TableName = "BPTPDTP";
        BPTPDTP_BR.rp.where = "SUPR_TYPE = :BPRPDTP.KEY.PRDT_TYPE";
        BPTPDTP_BR.rp.Reqid = 3;
        IBS.STARTBR(SCCGWA, BPRPDTP, this, BPTPDTP_BR);
    }
    public void T000_STARTBR_BPTPDTP_04() throws IOException,SQLException,Exception {
        BPTPDTP_BR.rp = new DBParm();
        BPTPDTP_BR.rp.TableName = "BPTPDTP";
        BPTPDTP_BR.rp.where = "PRDT_TYPE >= :BPRPDTP.KEY.PRDT_TYPE";
        BPTPDTP_BR.rp.Reqid = 4;
        IBS.STARTBR(SCCGWA, BPRPDTP, this, BPTPDTP_BR);
    }
    public void T000_STARTBR_BPTPDTP_05() throws IOException,SQLException,Exception {
        BPTPDTP_BR.rp = new DBParm();
        BPTPDTP_BR.rp.TableName = "BPTPDTP";
        BPTPDTP_BR.rp.where = "BOTTOM_IND = 'T'";
        BPTPDTP_BR.rp.Reqid = 5;
        IBS.STARTBR(SCCGWA, BPRPDTP, this, BPTPDTP_BR);
    }
    public void T000_STARTBR_BPTPDTP_06() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRPDTP.BOTTOM_IND);
        BPTPDTP_BR.rp = new DBParm();
        BPTPDTP_BR.rp.TableName = "BPTPDTP";
        BPTPDTP_BR.rp.where = "BOTTOM_IND = :BPRPDTP.BOTTOM_IND";
        BPTPDTP_BR.rp.Reqid = 6;
        IBS.STARTBR(SCCGWA, BPRPDTP, this, BPTPDTP_BR);
    }
    public void T000_READNEXT_BPTPDTP_1() throws IOException,SQLException,Exception {
        BPTPDTP_BR.rp.Reqid = 1;
        IBS.READNEXT(SCCGWA, BPRPDTP, this, BPTPDTP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBPDT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBPDT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTPDTP_2() throws IOException,SQLException,Exception {
        BPTPDTP_BR.rp.Reqid = 2;
        IBS.READNEXT(SCCGWA, BPRPDTP, this, BPTPDTP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBPDT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBPDT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTPDTP_3() throws IOException,SQLException,Exception {
        BPTPDTP_BR.rp.Reqid = 3;
        IBS.READNEXT(SCCGWA, BPRPDTP, this, BPTPDTP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBPDT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBPDT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTPDTP_4() throws IOException,SQLException,Exception {
        BPTPDTP_BR.rp.Reqid = 4;
        IBS.READNEXT(SCCGWA, BPRPDTP, this, BPTPDTP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBPDT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBPDT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTPDTP_5() throws IOException,SQLException,Exception {
        BPTPDTP_BR.rp.Reqid = 5;
        IBS.READNEXT(SCCGWA, BPRPDTP, this, BPTPDTP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBPDT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBPDT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTPDTP_6() throws IOException,SQLException,Exception {
        BPTPDTP_BR.rp.Reqid = 6;
        IBS.READNEXT(SCCGWA, BPRPDTP, this, BPTPDTP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBPDT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBPDT.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTPDTP_1() throws IOException,SQLException,Exception {
        BPTPDTP_BR.rp.Reqid = 1;
        IBS.ENDBR(SCCGWA, BPTPDTP_BR);
    }
    public void T000_ENDBR_BPTPDTP_2() throws IOException,SQLException,Exception {
        BPTPDTP_BR.rp.Reqid = 2;
        IBS.ENDBR(SCCGWA, BPTPDTP_BR);
    }
    public void T000_ENDBR_BPTPDTP_3() throws IOException,SQLException,Exception {
        BPTPDTP_BR.rp.Reqid = 3;
        IBS.ENDBR(SCCGWA, BPTPDTP_BR);
    }
    public void T000_ENDBR_BPTPDTP_4() throws IOException,SQLException,Exception {
        BPTPDTP_BR.rp.Reqid = 4;
        IBS.ENDBR(SCCGWA, BPTPDTP_BR);
    }
    public void T000_ENDBR_BPTPDTP_5() throws IOException,SQLException,Exception {
        BPTPDTP_BR.rp.Reqid = 5;
        IBS.ENDBR(SCCGWA, BPTPDTP_BR);
    }
    public void T000_ENDBR_BPTPDTP_6() throws IOException,SQLException,Exception {
        BPTPDTP_BR.rp.Reqid = 6;
        IBS.ENDBR(SCCGWA, BPTPDTP_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRBPDT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRBPDT = ");
            CEP.TRC(SCCGWA, BPCRBPDT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
