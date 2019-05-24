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

public class SOZCPSW {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm SOTUSR_RD;
    boolean pgmRtn = false;
    String PGM_SCZENPSW = "SCZENPSW";
    short WK_REC_LEN = 0;
    int WK_RESP = 0;
    short WK_I = 0;
    short WK_J = 0;
    SOCMSG SOCMSG = new SOCMSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SORUSR SORUSR1 = new SORUSR();
    SCCGWA SCCGWA;
    SOCCPSW SOCCPSW;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORUSR;
    String WK_INP_TLR_ID = " ";
    String WK_INP_TLR_PSW = " ";
    public void MP(SCCGWA SCCGWA, SOCCPSW SOCCPSW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SOCCPSW = SOCCPSW;
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
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SORUSR = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
        CEP.TRC(SCCGWA, SOCCPSW);
    }
    public void B00_CHK_PROC() throws IOException,SQLException,Exception {
    }
    public void C00_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SOCCPSW.TL_ID.equalsIgnoreCase(SORUSR.KEY.ID)) {
            if (SORUSR.PSW.trim().length() > 0) {
                IBS.init(SCCGWA, SCCENPSW);
                SCCENPSW.PSWD_IN = SOCCPSW.PSW;
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
                if (!SORUSR.PSW.equalsIgnoreCase(SCCENPSW.PSWD_OUT.substring(0, SCCENPSW.PSWD_LEN))) {
                    IBS.CPY2CLS(SCCGWA, SOCMSG.SO_TLR_PSW_ERR, SOCCPSW.RC);
                    CEP.ERR(SCCGWA, SOCMSG.SO_TLR_PSW_ERR);
                }
            }
        } else {
            T01_READ_ONLY_TELLER();
            if (pgmRtn) return;
            if (SORUSR1.PSW.trim().length() > 0) {
                IBS.init(SCCGWA, SCCENPSW);
                SCCENPSW.PSWD_IN = SOCCPSW.PSW;
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
                if (!SORUSR1.PSW.equalsIgnoreCase(SCCENPSW.PSWD_OUT.substring(0, SCCENPSW.PSWD_LEN))) {
                    CEP.ERR(SCCGWA, SOCMSG.SO_TLR_PSW_ERR);
                }
            }
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
    public void T01_READ_ONLY_TELLER() throws IOException,SQLException,Exception {
        SORUSR1.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORUSR1.KEY.ID = SOCCPSW.TL_ID;
        SOTUSR_RD = new DBParm();
        SOTUSR_RD.TableName = "SOTUSR";
        IBS.READ(SCCGWA, SORUSR1, SOTUSR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTUSR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
