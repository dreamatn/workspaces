package com.hisun.SO;

import com.hisun.BP.BPRTRT;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCENPSW;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGMSG;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

import java.io.IOException;
import java.sql.SQLException;

public class SOZCSUP {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String JIBS_NumStr;
    String JIBS_f0;
    DBParm SOTUSR_RD;
    boolean pgmRtn = false;
    String PGM_SOZCPRIV = "SOZCPRIV";
    String PGM_SCZENPSW = "SCZENPSW";
    short WK_REC_LEN = 0;
    int WK_RESP = 0;
    short WK_I = 0;
    short WK_J = 0;
    short WK_USR_LVL = 0;
    SOCMSG SOCMSG = new SOCMSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SOCCPRIV SOCCPRIV = new SOCCPRIV();
    SORUSR SORUSR = new SORUSR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGMSG SCCGMSG;
    BPRTRT SORSERV;
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
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        SORSERV = (BPRTRT) GWA_SC_AREA.TR_PARM_PTR;
    }
    public void B00_CHK_PROC() throws IOException,SQLException,Exception {
        if (SCCGMSG.SUP1_ID.equalsIgnoreCase(SCCGMSG.SUP2_ID)) {
            CEP.ERR(SCCGWA, SOCMSG.SO_SUP_MUST_DIF);
        }
        if (SORSERV.CTRL_WORD == null) SORSERV.CTRL_WORD = "";
        JIBS_tmp_int = SORSERV.CTRL_WORD.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) SORSERV.CTRL_WORD += " ";
        if (!SORSERV.CTRL_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            if (SCCGMSG.SUP1_ID.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID) 
                || SCCGMSG.SUP2_ID.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                CEP.ERR(SCCGWA, SOCMSG.SO_NO_SELF_AUTH);
            }
        }
    }
    public void C00_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGMSG.SUP1_ID.trim().length() > 0) {
            SORUSR.KEY.ID = SCCGMSG.SUP1_ID;
            T01_READ_USER();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = "" + WK_USR_LVL;
            JIBS_f0 = "";
            for (int i=0;i<2-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + WK_USR_LVL;
            JIBS_tmp_str[1] = "" + SORUSR.AUTH_LVL;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(1);
            WK_USR_LVL = Short.parseShort(JIBS_NumStr);
            IBS.init(SCCGWA, SCCENPSW);
            SCCENPSW.PSWD_IN = SCCGMSG.SUP1_PSW;
            SCCENPSW.PSWD_LEN = 20;
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
            }
            IBS.init(SCCGWA, SOCCPRIV);
            SOCCPRIV.TLR_ID = SCCGMSG.SUP1_ID;
            SOCCPRIV.SERV_ID = SCCGWA.COMM_AREA.SERV_CODE;
            SOZCPRIV SOZCPRIV = new SOZCPRIV();
            SOZCPRIV.MP(SCCGWA, SOCCPRIV);
            if (SOCCPRIV.RC.RC_CODE != 0) {
                CEP.ERR(SCCGWA, SOCMSG.SO_SUP_PRIV_ERR);
            }
        }
        if (SCCGMSG.SUP2_ID.trim().length() > 0) {
            SORUSR.KEY.ID = SCCGMSG.SUP2_ID;
            T01_READ_USER();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = "" + WK_USR_LVL;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (SORUSR.AUTH_LVL > JIBS_tmp_str[0].substring(0, 1)) {
                JIBS_tmp_str[0] = "" + WK_USR_LVL;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + WK_USR_LVL;
                JIBS_f0 = "";
                for (int i=0;i<2-JIBS_tmp_str[1].length();i++) JIBS_f0 += "0";
                JIBS_NumStr = JIBS_f0 + WK_USR_LVL;
                JIBS_NumStr = JIBS_NumStr.substring(0, 2 - 1) + JIBS_tmp_str[0].substring(0, 1) + JIBS_NumStr.substring(2 + 1 - 1);
                WK_USR_LVL = Short.parseShort(JIBS_NumStr);
                JIBS_tmp_str[0] = "" + WK_USR_LVL;
                JIBS_f0 = "";
                for (int i=0;i<2-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                JIBS_NumStr = JIBS_f0 + WK_USR_LVL;
                JIBS_tmp_str[1] = "" + SORUSR.AUTH_LVL;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(1);
                WK_USR_LVL = Short.parseShort(JIBS_NumStr);
            } else {
                JIBS_tmp_str[0] = "" + WK_USR_LVL;
                JIBS_f0 = "";
                for (int i=0;i<2-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                JIBS_NumStr = JIBS_f0 + WK_USR_LVL;
                JIBS_tmp_str[1] = "" + SORUSR.AUTH_LVL;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_NumStr = JIBS_NumStr.substring(0, 2 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(2 + 1 - 1);
                WK_USR_LVL = Short.parseShort(JIBS_NumStr);
            }
            IBS.init(SCCGWA, SCCENPSW);
            SCCENPSW.PSWD_IN = SCCGMSG.SUP2_PSW;
            SCCENPSW.PSWD_LEN = 20;
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
            }
            IBS.init(SCCGWA, SOCCPRIV);
            SOCCPRIV.TLR_ID = SCCGMSG.SUP2_ID;
            SOCCPRIV.SERV_ID = SCCGWA.COMM_AREA.SERV_CODE;
            SOZCPRIV SOZCPRIV = new SOZCPRIV();
            SOZCPRIV.MP(SCCGWA, SOCCPRIV);
            if (SOCCPRIV.RC.RC_CODE != 0) {
                CEP.ERR(SCCGWA, SOCMSG.SO_SUP_PRIV_ERR);
            }
        }
        if (WK_USR_LVL < SCCGMSG.AUTH_LVL_NEW) {
            CEP.ERR(SCCGWA, SOCMSG.SO_SUP_LVL_ERR);
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
    public void T01_READ_USER() throws IOException,SQLException,Exception {
        SORUSR.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SOTUSR_RD = new DBParm();
        SOTUSR_RD.TableName = "SOTUSR";
        IBS.READ(SCCGWA, SORUSR, SOTUSR_RD);
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
