package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTSQTP {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTSQTP_BR = new brParm();
    boolean pgmRtn = false;
    String PGM_BPZTSQTP = "BPZTSQTP";
    String TBL_BPTSQTP = "BPTSQTP ";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRSQTP BPRSQTP = new BPRSQTP();
    SCCGWA SCCGWA;
    BPCRSQTP BPCRSQTP;
    BPRSQTP BPRSQTQ;
    public void MP(SCCGWA SCCGWA, BPCRSQTP BPCRSQTP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRSQTP = BPCRSQTP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTSQTP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRSQTQ = (BPRSQTP) BPCRSQTP.INFO.POINTER;
        IBS.init(SCCGWA, BPRSQTP);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRSQTQ);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRSQTQ, BPRSQTP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRSQTP.INFO.FUNC == '1') {
            B011_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRSQTP.INFO.FUNC == '2') {
            B012_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRSQTP.INFO.FUNC == '3') {
            B013_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRSQTP.INFO.FUNC == 'R') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRSQTP.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRSQTP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRSQTP);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRSQTP, BPRSQTQ);
    }
    public void B011_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTSQTP1();
        if (pgmRtn) return;
    }
    public void B012_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTSQTP2();
        if (pgmRtn) return;
    }
    public void B013_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTSQTP3();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTSQTP();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTSQTP();
        if (pgmRtn) return;
    }
    public void T000_READNEXT_BPTSQTP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRSQTP, this, BPTSQTP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRSQTP.INFO.RTN_INFO = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRSQTP.INFO.RTN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTSQTP1() throws IOException,SQLException,Exception {
        BPTSQTP_BR.rp = new DBParm();
        BPTSQTP_BR.rp.TableName = "BPTSQTP";
        BPTSQTP_BR.rp.order = "EXR_TYP, CCY";
        IBS.STARTBR(SCCGWA, BPRSQTP, BPTSQTP_BR);
    }
    public void T000_STARTBR_BPTSQTP2() throws IOException,SQLException,Exception {
        BPTSQTP_BR.rp = new DBParm();
        BPTSQTP_BR.rp.TableName = "BPTSQTP";
        BPTSQTP_BR.rp.where = "EXR_TYP LIKE :BPRSQTP.KEY.EXR_TYP "
            + "AND CCY LIKE :BPRSQTP.KEY.CCY";
        IBS.STARTBR(SCCGWA, BPRSQTP, this, BPTSQTP_BR);
    }
    public void T000_STARTBR_BPTSQTP3() throws IOException,SQLException,Exception {
        BPTSQTP_BR.rp = new DBParm();
        BPTSQTP_BR.rp.TableName = "BPTSQTP";
        BPTSQTP_BR.rp.where = "EXR_TYP = :BPRSQTP.KEY.EXR_TYP";
        BPTSQTP_BR.rp.order = "CCY";
        IBS.STARTBR(SCCGWA, BPRSQTP, this, BPTSQTP_BR);
    }
    public void T000_ENDBR_BPTSQTP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTSQTP_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRSQTP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRSQTP = ");
            CEP.TRC(SCCGWA, BPCRSQTP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
