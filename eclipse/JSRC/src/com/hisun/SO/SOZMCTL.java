package com.hisun.SO;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGMSG;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;

public class SOZMCTL {
    boolean pgmRtn = false;
    String PGM_SCZOMSG = "SCZOMSG ";
    String PGM_SOZCPRIV = "SOZCPRIV";
    String PGM_SOZCSUP = "SOZCSUP ";
    String PGM_SOZCWAIT = "SOZCWAIT";
    String PGM_SOZMENU = "SOZMENU ";
    String PGM_SCZCAUR = "SCZCAUR ";
    String PGM_SOZWJRN = "SOZWJRN ";
    String PGM_SOZSMGJ = "SOZSMGJ";
    short WK_REC_LEN = 0;
    int WK_RESP = 0;
    short WK_I = 0;
    short WK_J = 0;
    char WK_FLOW_IND = ' ';
    char WK_AUTH_LVL1 = ' ';
    char WK_AUTH_LVL2 = ' ';
    String WK_ABCD = " ";
    String WK_AB_PGM = " ";
    String WK_AB_PSW = " ";
    String WK_PROC = "SCPJTAP";
    String WK_PARM = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSMSG SCCSMSG = new SCCSMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SORSYS SORSYS = new SORSYS();
    SORUSR SORUSR = new SORUSR();
    SORSERV SORSERV = new SORSERV();
    SOCCPRIV SOCCPRIV = new SOCCPRIV();
    SOCSMGJ SOCSMGJ = new SOCSMGJ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGMSG SCCGMSG;
    SCRCWAT SCRCWA;
    String WK_PROC_PARM = " ";
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
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
        WK_PROC_PARM = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.BAT_AREA_PTR);
        GWA_SC_AREA.TR_PARM_PTR = SORSERV;
        SCCGWA.COMM_AREA.TLT_AREA_PTR = SORUSR;
        A01_GET_INFO();
        if (pgmRtn) return;
    }
    public void A01_GET_INFO() throws IOException,SQLException,Exception {
        A01_05_GET_SERV();
        if (pgmRtn) return;
        A01_20_GET_TLR();
        if (pgmRtn) return;
        A01_30_GET_JRN();
        if (pgmRtn) return;
    }
    public void A01_05_GET_SERV() throws IOException,SQLException,Exception {
        SORSERV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORSERV.KEY.ID = SCCGWA.COMM_AREA.SERV_CODE;
        CEP.TRC(SCCGWA, SORSERV.KEY);
        CEP.TRC(SCCGWA, SORSERV.KEY.ID);
        SOTSERV_RD = new DBParm();
        SOTSERV_RD.TableName = "SOTSERV";
        IBS.READ(SCCGWA, SORSERV, SOTSERV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (GWA_SC_AREA.REQ_TYPE != 'E') {
                IBS.CPY2CLS(SCCGWA, SORSERV.SUB_SERV, GWA_SC_AREA.TR_ATTR_AREA.SUBS_TR);
            }
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, SORSERV.KEY.ID);
            CEP.ERR(SCCGWA, SOCMSG.SO_SERV_NOTFND);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTSERV";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void A01_20_GET_TLR() throws IOException,SQLException,Exception {
        SORUSR.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORUSR.KEY.ID = SCCGWA.COMM_AREA.TL_ID;
        SOTUSR_RD = new DBParm();
        SOTUSR_RD.TableName = "SOTUSR";
        SOTUSR_RD.upd = true;
        IBS.READ(SCCGWA, SORUSR, SOTUSR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, SOCMSG.SO_OPER_NOTFND);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTUSR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void A01_30_GET_JRN() throws IOException,SQLException,Exception {
        if (SORSERV.CTRL_WORD == null) SORSERV.CTRL_WORD = "";
        JIBS_tmp_int = SORSERV.CTRL_WORD.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) SORSERV.CTRL_WORD += " ";
        if (SORSERV.CTRL_WORD.substring(0, 1).equalsIgnoreCase("1")) {
            SORSYS.KEY.SYS_ID = " ";
            SOTSYS_RD = new DBParm();
            SOTSYS_RD.TableName = "SOTSYS";
            SOTSYS_RD.upd = true;
            IBS.READ(SCCGWA, SORSYS, SOTSYS_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, SOCMSG.SO_SYS_REC_NOTFND);
            } else {
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTSYS";
                SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
                SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
                B_DB_EXCP();
                if (pgmRtn) return;
            }
            SCCGWA.COMM_AREA.JRN_NO = SORSYS.JRN_NO;
            SORSYS.JRN_NO += 1;
            SCCGWA.COMM_AREA.POST_DATE = SORSYS.OPER_DATE;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SYS_OPT);
            if (SCCGWA.COMM_AREA.SYS_OPT != 'Q') {
                SOTSYS_RD = new DBParm();
                SOTSYS_RD.TableName = "SOTSYS";
                IBS.REWRITE(SCCGWA, SORSYS, SOTSYS_RD);
                if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                    IBS.init(SCCGWA, SCCEXCP);
                    SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTSYS";
                    SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
                    SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
                    B_DB_EXCP();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B00_CHK_PROC() throws IOException,SQLException,Exception {
        B01_CHK_SYS();
        if (pgmRtn) return;
        B10_CHK_TLR();
        if (pgmRtn) return;
    }
    public void B01_CHK_SYS() throws IOException,SQLException,Exception {
    }
    public void B10_CHK_TLR() throws IOException,SQLException,Exception {
        B10_01_CHK_TLR_STUS();
        if (pgmRtn) return;
    }
    public void B10_01_CHK_TLR_STUS() throws IOException,SQLException,Exception {
        if (SORSERV.CTRL_WORD == null) SORSERV.CTRL_WORD = "";
        JIBS_tmp_int = SORSERV.CTRL_WORD.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) SORSERV.CTRL_WORD += " ";
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE != 'E' 
            && SORSERV.CTRL_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("0")) {
            if (SORUSR.SNON_STUS == 'F') {
                CEP.ERR(SCCGWA, SOCMSG.SO_TLR_SIGN_OFF);
            }
            if (!SORUSR.TERM_ID.equalsIgnoreCase(SCCGWA.COMM_AREA.TERM_ID)) {
                CEP.ERR(SCCGWA, SOCMSG.SO_SIGN_TERM_ERR);
            }
        }
    }
    public void B10_05_CHK_TLR_PRIV() throws IOException,SQLException,Exception {
        if (SORSERV.CTRL_WORD == null) SORSERV.CTRL_WORD = "";
        JIBS_tmp_int = SORSERV.CTRL_WORD.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) SORSERV.CTRL_WORD += " ";
        if ((SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE != 'E') 
            && SORSERV.CTRL_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("0")) {
            IBS.init(SCCGWA, SOCCPRIV);
            SOCCPRIV.TLR_ID = SCCGWA.COMM_AREA.TL_ID;
            SOZCPRIV SOZCPRIV = new SOZCPRIV();
            SOZCPRIV.MP(SCCGWA, SOCCPRIV);
        }
    }
    public void B10_10_CHK_SUP_AUTH() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'N' 
            && (SCCGMSG.SUP1_ID.trim().length() > 0 
            || SCCGMSG.SUP1_ID.trim().length() > 0)) {
            SOZCSUP SOZCSUP = new SOZCSUP();
            SOZCSUP.MP(SCCGWA, null);
        }
    }
    public void B10_15_CHK_WAIT_SERV() throws IOException,SQLException,Exception {
        if (GWA_SC_AREA.REQ_TYPE == 'T' 
            && (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE != 'E')) {
            SOZCWAIT SOZCWAIT = new SOZCWAIT();
            SOZCWAIT.MP(SCCGWA, null);
        }
    }
    public void C00_MAIN_PROC() throws IOException,SQLException,Exception {
        C01_STAT_AUTH();
        if (pgmRtn) return;
        C05_PROG_SCH();
        if (pgmRtn) return;
        C20_CHK_AUTH();
        if (pgmRtn) return;
        C30_WRITE_JRN();
        if (pgmRtn) return;
        C35_UPDATE_TLR();
        if (pgmRtn) return;
        C40_SUB_JOB();
        if (pgmRtn) return;
    }
    public void C01_STAT_AUTH() throws IOException,SQLException,Exception {
        if (GWA_SC_AREA.REQ_TYPE == 'T' 
            && (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE != 'E') 
            && (!SORSERV.AUTH_LVL.equalsIgnoreCase("00"))) {
            if (SORSERV.AUTH_LVL == null) SORSERV.AUTH_LVL = "";
            JIBS_tmp_int = SORSERV.AUTH_LVL.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) SORSERV.AUTH_LVL += " ";
            WK_AUTH_LVL1 = SORSERV.AUTH_LVL.substring(0, 1).charAt(0);
            if (SORSERV.AUTH_LVL == null) SORSERV.AUTH_LVL = "";
            JIBS_tmp_int = SORSERV.AUTH_LVL.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) SORSERV.AUTH_LVL += " ";
            WK_AUTH_LVL2 = SORSERV.AUTH_LVL.substring(2 - 1, 2 + 1 - 1).charAt(0);
            SCCMSG SCCMSG = new SCCMSG();
            IBS.init(SCCGWA, SCCMSG);
            SCCMSG.MSGID = SOCMSG.SO_SERV_AUTH;
            SCCMSG.TYPE = (char) 'A';
            SCCMSG.LVL.LVL1 = WK_AUTH_LVL1;
            SCCMSG.LVL.LVL2 = WK_AUTH_LVL2;
            CEP.ERR(SCCGWA, SCCMSG);
        }
    }
    public void C05_PROG_SCH() throws IOException,SQLException,Exception {
