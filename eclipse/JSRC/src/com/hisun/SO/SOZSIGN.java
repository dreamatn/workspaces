package com.hisun.SO;

import com.hisun.BP.BPRTLT;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCENPSW;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

import java.io.IOException;
import java.sql.SQLException;

public class SOZSIGN {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String SCZENPSW = "SCZENPSW";
    String SOZATRM = "SOZATRM";
    short WK_REC_LEN = 0;
    int WK_RESP = 0;
    short WK_I = 0;
    short WK_J = 0;
    SOCMSG SOCMSG = new SOCMSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SOCATRM SOCATRM = new SOCATRM();
    SORTERM SORTERM = new SORTERM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    BPRTLT BPRTLT;
    SORUSR_WK_INP_DATA WK_INP_DATA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_CHK_PROC();
        if (pgmRtn) return;
        C00_MAIN_PROC();
        if (pgmRtn) return;
        D00_OUTP_PROC();
        if (pgmRtn) return;
        E00_GO_BACK();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WK_INP_DATA = new SORUSR_WK_INP_DATA();
        IBS.init(SCCGWA, WK_INP_DATA);
        IBS.CPY2CLS(SCCGWA, SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, WK_INP_DATA);
        BPRTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_CHK_PROC() throws IOException,SQLException,Exception {
    }
    public void C00_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SC_AREA.REQ_TYPE == 'T') {
            C01_CHK_PSW();
            if (pgmRtn) return;
            C05_SIGN_ON_PROC();
            if (pgmRtn) return;
        }
        if (SC_AREA.REQ_TYPE == 'E') {
            C10_SIGN_OFF_PROC();
            if (pgmRtn) return;
        }
    }
    public void C01_CHK_PSW() throws IOException,SQLException,Exception {
        if (BPRTLT.PSW.trim().length() > 0) {
            IBS.init(SCCGWA, SCCENPSW);
            SCCENPSW.PSWD_IN = WK_INP_DATA.INP_TLR_PSW;
            SCCENPSW.PSWD_LEN = 6;
            SCZENPSW SCZENPSW = new SCZENPSW();
            SCZENPSW.MP(SCCGWA, SCCENPSW);
            if (SCCENPSW.RC.RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCENPSW.RC);
                CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
            }
            if (SCCENPSW.PSWD_OUT == null) SCCENPSW.PSWD_OUT = "";
            JIBS_tmp_int = SCCENPSW.PSWD_OUT.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) SCCENPSW.PSWD_OUT += " ";
            if (!BPRTLT.PSW.equalsIgnoreCase(SCCENPSW.PSWD_OUT.substring(0, SCCENPSW.PSWD_LEN))) {
                CEP.ERR(SCCGWA, SOCMSG.SO_TLR_PSW_ERR);
            }
        }
    }
    public void C05_SIGN_ON_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTLT.SNON_STUS);
        if (BPRTLT.SNON_STUS == 'O') {
            if (BPRTLT.SNON_DATE < SCCGWA.COMM_AREA.TR_DATE) {
            } else {
                CEP.ERR(SCCGWA, SOCMSG.SO_TLR_SIGN_ON);
            }
        }
        if (BPRTLT.SNON_STUS == 'F') {
            BPRTLT.SNON_STUS = 'O';
            BPRTLT.TERM_ID = SCCGWA.COMM_AREA.TERM_ID;
            BPRTLT.SNON_DATE = SCCGWA.COMM_AREA.TR_DATE;
            BPRTLT.SNON_TIME = SCCGWA.COMM_AREA.TR_TIME;
            SORTERM.KEY.ID = SCCGWA.COMM_AREA.TERM_ID;
            T05_READ_UPD_TERM();
            if (pgmRtn) return;
            SORTERM.USR = SCCGWA.COMM_AREA.TL_ID;
            SORTERM.SNON_DATE = SCCGWA.COMM_AREA.TR_DATE;
            SORTERM.SNON_TIME = SCCGWA.COMM_AREA.TR_TIME;
            SORTERM.SNON_STUS = 'Y';
            T10_REWRITE_TERM();
            if (pgmRtn) return;
        }
    }
    public void C10_SIGN_OFF_PROC() throws IOException,SQLException,Exception {
        if (BPRTLT.SNON_STUS == 'F') {
            CEP.ERR(SCCGWA, SOCMSG.SO_TLR_SIGN_OFF);
        }
        if (BPRTLT.SNON_STUS == 'O') {
            BPRTLT.SNON_STUS = 'F';
            SORTERM.KEY.ID = SCCGWA.COMM_AREA.TERM_ID;
            T05_READ_UPD_TERM();
            if (pgmRtn) return;
            SORTERM.SNON_STUS = 'N';
            T10_REWRITE_TERM();
            if (pgmRtn) return;
        }
    }
    public void D00_OUTP_PROC() throws IOException,SQLException,Exception {
    }
    public void E00_GO_BACK() throws IOException,SQLException,Exception {
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void T01_READ_ONLY_TERM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        SORTERM.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        IBS.init(SCCGWA, SOCATRM);
        SOCATRM.DATA_PTR = SORTERM;
        SOCATRM.FUNC = '1';
        SOZATRM SOZATRM = new SOZATRM();
        SOZATRM.MP(SCCGWA, SOCATRM);
    }
    public void T05_READ_UPD_TERM() throws IOException,SQLException,Exception {
        SORTERM.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        IBS.init(SCCGWA, SOCATRM);
        SOCATRM.DATA_PTR = SORTERM;
        SOCATRM.FUNC = '2';
        SOZATRM SOZATRM = new SOZATRM();
        SOZATRM.MP(SCCGWA, SOCATRM);
    }
    public void T10_REWRITE_TERM() throws IOException,SQLException,Exception {
        SOCATRM.FUNC = '3';
        SOZATRM SOZATRM = new SOZATRM();
        SOZATRM.MP(SCCGWA, SOCATRM);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
