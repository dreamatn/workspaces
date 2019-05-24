package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZQQTP {
    DBParm BPTTQP_RD;
    brParm BPTTQPH_BR = new brParm();
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCKDT = "SCSSCKDT";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQ_PUB_CODE = "BP-P-INQ-PC       ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_R_TQPH = "BP-R-TQPH-B      ";
    String CPN_R_EXRM = "BP-R-EXRD-M       ";
    String CPN_INQ_EXR_CODE = "BP-INQ-EXR-CODE   ";
    String CPN_INQ_EXR_RATE = "BP-INQ-TRN-QTP    ";
    String K_FWD_TENOR = "FWDT ";
    int K_MAX_ROW = 50;
    int K_MAX_COL = 250;
    int K_Q_MAX_CNT = 1000;
    int K_MAX_TIME = 235959;
    int WS_BR = 0;
    String WS_CCY = " ";
    int WS_DATE = 0;
    String WS_FWD_TENOR = " ";
    int WS_UNIT = 0;
    int WS_UNIT1 = 0;
    int WS_UNIT2 = 0;
    String WS_ERR_MSG = " ";
    BPZQQTP_WS_R_S_DT WS_R_S_DT = new BPZQQTP_WS_R_S_DT();
    BPZQQTP_WS_R_E_DT WS_R_E_DT = new BPZQQTP_WS_R_E_DT();
    BPZQQTP_WS_I_S_DT WS_I_S_DT = new BPZQQTP_WS_I_S_DT();
    BPZQQTP_WS_I_E_DT WS_I_E_DT = new BPZQQTP_WS_I_E_DT();
    short WS_MPAG_CNT = 0;
    BPZQQTP_WS_MPAG_DATA WS_MPAG_DATA = new BPZQQTP_WS_MPAG_DATA();
    BPZQQTP_WS_MPAG_DATA_X WS_MPAG_DATA_X = new BPZQQTP_WS_MPAG_DATA_X();
    char WS_OUT_REC_FLG = 'N';
    char WS_BUY_METH = ' ';
    char WS_SELL_METH = ' ';
    char WS_METH_1 = ' ';
    char WS_METH_2 = ' ';
    char WS_BASE_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    BPCRTQPH BPCRTQPH = new BPCRTQPH();
    BPCOIEC BPCOIEC = new BPCOIEC();
    BPCITQ BPCITQ = new BPCITQ();
    SCCFMT SCCFMT = new SCCFMT();
    BPRTQPH BPRTQPH = new BPRTQPH();
    BPRTQPH BPRTQP1 = new BPRTQPH();
    BPRTQPH BPRTQP2 = new BPRTQPH();
    BPREXRD BPREXRD = new BPREXRD();
    BPRTQP BPRTQP = new BPRTQP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCQQTP BPCQQTP;
    public void MP(SCCGWA SCCGWA, BPCQQTP BPCQQTP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCQQTP = BPCQQTP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZQQTP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCQQTP.CCY);
        CEP.TRC(SCCGWA, BPCQQTP.CORR_CCY);
        CEP.TRC(SCCGWA, BPCQQTP.STR_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPCQQTP.STR_TIME);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        CEP.TRC(SCCGWA, BPCQQTP.STR_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if ((BPCQQTP.STR_DATE == SCCGWA.COMM_AREA.AC_DATE 
            && BPCQQTP.STR_TIME >= SCCGWA.COMM_AREA.TR_TIME) 
            || BPCQQTP.STR_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            B006_INQ_BPTTQP();
            if (pgmRtn) return;
        } else {
            B003_INQ_PROC();
            if (pgmRtn) return;
        }
    }
    public void B006_INQ_BPTTQP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTQP);
        BPRTQP.KEY.BR = BPCQQTP.BR;
        BPRTQP.KEY.EXR_TYP = BPCQQTP.EXR_TYP;
        BPRTQP.KEY.CCY = BPCQQTP.CCY;
        BPRTQP.KEY.CORR_CCY = BPCQQTP.CORR_CCY;
        CEP.TRC(SCCGWA, "DEVHZ298");
        T000_READ_REC_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            B003_01_OUT_TITLE();
            if (pgmRtn) return;
            C000_OUT_TQP_DATA();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        BPTTQP_RD = new DBParm();
        BPTTQP_RD.TableName = "BPTTQP";
        BPTTQP_RD.where = "BR = :BPRTQP.KEY.BR "
            + "AND EXR_TYP = :BPRTQP.KEY.EXR_TYP "
            + "AND CCY = :BPRTQP.KEY.CCY "
            + "AND CORR_CCY = :BPRTQP.KEY.CORR_CCY";
        IBS.READ(SCCGWA, BPRTQP, this, BPTTQP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCQQTP.EXR_TYP.trim().length() > 0) {
            IBS.init(SCCGWA, BPREXRT);
            IBS.init(SCCGWA, BPCPRMR);
            BPREXRT.KEY.TYP = "EXRT";
            BPREXRT.KEY.CD = BPCQQTP.EXR_TYP;
            BPCPRMR.DAT_PTR = BPREXRT;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            if (BPCPRMR.RC.RC_RTNCODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INVALID_EXR_TYP;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCQQTP.FWD_TENOR.trim().length() > 0) {
            WS_FWD_TENOR = BPCQQTP.FWD_TENOR;
            R000_CHECK_FWD_TENOR();
            if (pgmRtn) return;
        }
        if (BPCQQTP.CCY.trim().length() > 0) {
            WS_CCY = BPCQQTP.CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
        }
        if (BPCQQTP.CORR_CCY.trim().length() > 0) {
            WS_CCY = BPCQQTP.CORR_CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
        }
        if (BPCQQTP.STR_DATE != 0) {
            WS_DATE = BPCQQTP.STR_DATE;
            R000_CHECK_DATE();
            if (pgmRtn) return;
        } else {
            BPCQQTP.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPCQQTP.END_DATE != 0) {
            WS_DATE = BPCQQTP.END_DATE;
            R000_CHECK_DATE();
            if (pgmRtn) return;
        } else {
            BPCQQTP.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPCQQTP.STR_DATE > BPCQQTP.END_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STR_GT_END;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((BPCQQTP.CCY.trim().length() > 0 
            && !BPCQQTP.CCY.equalsIgnoreCase(BPREXRT.DAT_TXT.BASE_CCY) 
            && BPCQQTP.CORR_CCY.trim().length() > 0 
            && !BPCQQTP.CORR_CCY.equalsIgnoreCase(BPREXRT.DAT_TXT.BASE_CCY) 
            && BPCQQTP.EXR_TYP.trim().length() > 0 
            && BPREXRT.DAT_TXT.FWD_IND == '1' 
            && BPCQQTP.FWD_TENOR.trim().length() == 0)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_IN_FWD_TENOR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCQQTP.END_TIME);
        if (BPCQQTP.STR_DATE == SCCGWA.COMM_AREA.AC_DATE 
            && BPCQQTP.STR_TIME == 0) {
            BPCQQTP.STR_TIME = SCCGWA.COMM_AREA.TR_TIME;
        }
        if (BPCQQTP.END_DATE == SCCGWA.COMM_AREA.AC_DATE 
            && BPCQQTP.END_TIME == 0) {
            BPCQQTP.END_TIME = SCCGWA.COMM_AREA.TR_TIME;
        } else {
            if (BPCQQTP.END_TIME == 0) {
                BPCQQTP.END_TIME = K_MAX_TIME;
            }
        }
    }
    public void B003_INQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTQPH);
        IBS.init(SCCGWA, BPCRTQPH);
        BPRTQPH.KEY.BR = BPCQQTP.BR;
        if (BPCQQTP.EXR_TYP.trim().length() == 0) {
            BPRTQPH.KEY.EXR_TYP = "%%%";
        } else {
            BPRTQPH.KEY.EXR_TYP = BPCQQTP.EXR_TYP;
        }
        if (BPCQQTP.FWD_TENOR.trim().length() == 0) {
            BPRTQPH.KEY.FWD_TENOR = "%%%";
        } else {
            BPRTQPH.KEY.FWD_TENOR = BPCQQTP.FWD_TENOR;
        }
        BPRTQPH.KEY.BR = BPCQQTP.BR;
        if (BPCQQTP.CCY.trim().length() == 0) {
            BPRTQPH.KEY.CCY = "%%%";
        } else {
            BPRTQPH.KEY.CCY = BPCQQTP.CCY;
        }
        if (BPCQQTP.CCY.equalsIgnoreCase(BPREXRT.DAT_TXT.BASE_CCY) 
            && BPCQQTP.CORR_CCY.trim().length() > 0) {
            CEP.TRC(SCCGWA, "????");
            BPRTQPH.KEY.CCY = BPCQQTP.CORR_CCY;
            WS_BASE_FLAG = 'Y';
        }
        CEP.TRC(SCCGWA, BPREXRT.DAT_TXT.BASE_CCY);
        if (BPCQQTP.CORR_CCY.equalsIgnoreCase(BPREXRT.DAT_TXT.BASE_CCY) 
            && BPCQQTP.CCY.trim().length() > 0) {
            CEP.TRC(SCCGWA, "????");
            BPRTQPH.KEY.CCY = BPCQQTP.CCY;
            WS_BASE_FLAG = 'N';
        }
        BPRTQPH.KEY.CORR_CCY = BPCQQTP.CORR_CCY;
        BPRTQPH.EFF_DT = BPCQQTP.STR_DATE;
        BPCRTQPH.STR_DATE = BPCQQTP.STR_DATE;
        WS_I_S_DT.WS_I_S_DATE = BPCQQTP.STR_DATE;
        BPRTQPH.EFF_TM = BPCQQTP.STR_TIME;
        WS_I_S_DT.WS_I_S_TIME = BPCQQTP.STR_TIME;
        BPRTQPH.KEY.EXP_DT = BPCQQTP.END_DATE;
        BPCRTQPH.END_DATE = BPCQQTP.END_DATE;
        WS_I_E_DT.WS_I_E_DATE = BPCQQTP.END_DATE;
        BPRTQPH.KEY.EXP_TM = BPCQQTP.END_TIME;
        WS_I_E_DT.WS_I_E_TIME = BPCQQTP.END_TIME;
        BPCRTQPH.INFO.FUNC = '7';
        S000_CALL_STARTBR_H();
        if (pgmRtn) return;
        BPCRTQPH.INFO.FUNC = 'R';
        S000_CALL_READNEXT_H();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            B003_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_MPAG_CNT <= K_Q_MAX_CNT 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, WS_R_S_DT);
            IBS.init(SCCGWA, WS_R_E_DT);
            WS_OUT_REC_FLG = ' ';
            WS_R_S_DT.WS_R_S_DATE = BPRTQPH.EFF_DT;
            WS_R_S_DT.WS_R_S_TIME = BPRTQPH.EFF_TM;
            WS_R_E_DT.WS_R_E_DATE = BPRTQPH.KEY.EXP_DT;
            WS_R_E_DT.WS_R_E_TIME = BPRTQPH.KEY.EXP_TM;
            R000_CHK_DATE_TIME();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPZQQTP_WS2);
            CEP.TRC(SCCGWA, WS_R_S_DT);
            CEP.TRC(SCCGWA, WS_R_E_DT);
            CEP.TRC(SCCGWA, WS_I_S_DT);
            CEP.TRC(SCCGWA, WS_I_E_DT);
            if (WS_OUT_REC_FLG == 'Y') {
                B003_03_OUT_DATA();
                if (pgmRtn) return;
            }
            S000_CALL_READNEXT_H();
            if (pgmRtn) return;
        }
        S000_CALL_ENDBR();
        if (pgmRtn) return;
    }
    public void S000_CALL_STARTBR_H() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTQPH.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.BR);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.CCY);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.CORR_CCY);
        CEP.TRC(SCCGWA, BPRTQPH.EFF_DT);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.EXP_DT);
        CEP.TRC(SCCGWA, "DEVHZ-STR-H");
        BPTTQPH_BR.rp = new DBParm();
        BPTTQPH_BR.rp.TableName = "BPTTQPH";
        BPTTQPH_BR.rp.where = "EXR_TYP = :BPRTQPH.KEY.EXR_TYP "
            + "AND CCY = :BPRTQPH.KEY.CCY "
            + "AND BR = :BPRTQPH.KEY.BR "
            + "AND CORR_CCY = :BPRTQPH.KEY.CORR_CCY "
            + "AND ( EFF_DT <= :BPRTQPH.KEY.EXP_DT "
            + "AND EXP_DT >= :BPRTQPH.EFF_DT )";
        BPTTQPH_BR.rp.order = "CCY, EFF_DT DESC,EFF_TM DESC";
        IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
    }
    public void S000_CALL_READNEXT_H() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_ENDBR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTQPH_BR);
    }
    public void B003_01_OUT_TITLE() throws IOException,SQLException,Exception {
        WS_MPAG_CNT += 1;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 194;
        CEP.TRC(SCCGWA, SCCMPAG.MAX_COL_NO);
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B003_03_OUT_DATA() throws IOException,SQLException,Exception {
        WS_MPAG_CNT += 1;
        IBS.init(SCCGWA, WS_MPAG_DATA);
        WS_MPAG_DATA.WS_L_BR = BPRTQPH.KEY.BR;
        CEP.TRC(SCCGWA, BPRTQPH.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.CORR_CCY);
        if (BPCQQTP.EXR_TYP.trim().length() == 0) {
            IBS.init(SCCGWA, BPREXRT);
            IBS.init(SCCGWA, BPCPRMR);
            BPREXRT.KEY.TYP = "EXRT";
            BPREXRT.KEY.CD = BPRTQPH.KEY.EXR_TYP;
            CEP.TRC(SCCGWA, BPRTQPH.KEY.EXR_TYP);
            BPCPRMR.DAT_PTR = BPREXRT;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            if (BPCPRMR.RC.RC_RTNCODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INVALID_EXR_TYP;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPCOIEC);
            BPCOIEC.CCY1 = BPRTQPH.KEY.CCY;
            BPCOIEC.CCY2 = BPREXRT.DAT_TXT.BASE_CCY;
            BPCOIEC.EXR_TYP = BPRTQPH.KEY.EXR_TYP;
            S000_CALL_BPZSIEC();
            if (pgmRtn) return;
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            WS_MPAG_DATA.WS_L_CCY_PAIR.WS_L_CCY = BPCOIEC.EXR_CODE.substring(0, 3);
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            WS_MPAG_DATA.WS_L_CCY_PAIR.WS_L_CORR_CCY = BPCOIEC.EXR_CODE.substring(4 - 1, 4 + 3 - 1);
        } else {
            if (BPRTQPH.KEY.CORR_CCY.trim().length() == 0) {
                if (WS_BASE_FLAG == 'Y') {
                    WS_MPAG_DATA.WS_L_CCY_PAIR.WS_L_CCY = BPREXRT.DAT_TXT.BASE_CCY;
                    WS_MPAG_DATA.WS_L_CCY_PAIR.WS_L_CORR_CCY = BPRTQPH.KEY.CCY;
                } else {
                    WS_MPAG_DATA.WS_L_CCY_PAIR.WS_L_CCY = BPRTQPH.KEY.CCY;
                    WS_MPAG_DATA.WS_L_CCY_PAIR.WS_L_CORR_CCY = BPREXRT.DAT_TXT.BASE_CCY;
                }
            } else {
                WS_MPAG_DATA.WS_L_CCY_PAIR.WS_L_CCY = BPRTQPH.KEY.CCY;
                WS_MPAG_DATA.WS_L_CCY_PAIR.WS_L_CORR_CCY = BPRTQPH.KEY.CORR_CCY;
            }
        }
        WS_MPAG_DATA.WS_L_EXR_TYP = BPRTQPH.KEY.EXR_TYP;
        CEP.TRC(SCCGWA, WS_MPAG_DATA.WS_L_EXR_TYP);
        CEP.TRC(SCCGWA, "OVER1");
        if (BPCQQTP.CCY.equalsIgnoreCase(BPREXRT.DAT_TXT.BASE_CCY) 
            && BPCQQTP.CORR_CCY.trim().length() > 0) {
            CEP.TRC(SCCGWA, "????");
            WS_BASE_FLAG = 'Y';
        }
        if (BPCQQTP.CORR_CCY.equalsIgnoreCase(BPREXRT.DAT_TXT.BASE_CCY) 
            && BPCQQTP.CCY.trim().length() > 0) {
            CEP.TRC(SCCGWA, "????");
            WS_BASE_FLAG = 'N';
        }
        CEP.TRC(SCCGWA, "OVER");
        CEP.TRC(SCCGWA, WS_BASE_FLAG);
        CEP.TRC(SCCGWA, BPRTQPH.CS_BUY);
        CEP.TRC(SCCGWA, BPRTQPH.CS_SELL);
        CEP.TRC(SCCGWA, BPRTQPH.FX_BUY);
        CEP.TRC(SCCGWA, BPRTQPH.FX_SELL);
        CEP.TRC(SCCGWA, BPRTQPH.CCS_BUY);
        CEP.TRC(SCCGWA, BPRTQPH.CCS_SELL);
        CEP.TRC(SCCGWA, BPRTQPH.CFX_BUY);
        CEP.TRC(SCCGWA, BPRTQPH.CFX_SELL);
        CEP.TRC(SCCGWA, "DEVHZ-TQPH");
        WS_MPAG_DATA.WS_L_BR = BPRTQPH.KEY.BR;
        WS_MPAG_DATA.WS_L_EXR_TYP = BPRTQPH.KEY.EXR_TYP;
        WS_MPAG_DATA.WS_L_CCY_PAIR.WS_L_CCY = BPRTQPH.KEY.CCY;
        WS_MPAG_DATA.WS_L_CCY_PAIR.WS_L_CORR_CCY = BPRTQPH.KEY.CORR_CCY;
        WS_MPAG_DATA.WS_L_EFF_DATE = BPRTQPH.EFF_DT;
        WS_MPAG_DATA.WS_L_EFF_TIME = BPRTQPH.EFF_TM;
        WS_MPAG_DATA.WS_L_EXP_DATE = BPRTQPH.KEY.EXP_DT;
        WS_MPAG_DATA.WS_L_EXP_TIME = BPRTQPH.KEY.EXP_TM;
        WS_MPAG_DATA.WS_L_MID = BPRTQPH.LOC_MID;
        WS_MPAG_DATA.WS_L_CCS_BUY = BPRTQPH.CCS_BUY;
        WS_MPAG_DATA.WS_L_CCS_SELL = BPRTQPH.CCS_SELL;
        WS_MPAG_DATA.WS_L_CFX_BUY = BPRTQPH.CFX_BUY;
        WS_MPAG_DATA.WS_L_CFX_SELL = BPRTQPH.CFX_SELL;
        WS_MPAG_DATA.WS_L_CS_BUY = BPRTQPH.CS_BUY;
        WS_MPAG_DATA.WS_L_CS_SELL = BPRTQPH.CS_SELL;
        WS_MPAG_DATA.WS_L_FX_BUY = BPRTQPH.FX_BUY;
        WS_MPAG_DATA.WS_L_FX_SELL = BPRTQPH.FX_SELL;
        WS_MPAG_DATA.WS_L_CRE_DT = BPRTQPH.UPDTBL_DATE;
        WS_MPAG_DATA.WS_L_TELLER = BPRTQPH.UPD_TLR;
        CEP.TRC(SCCGWA, WS_MPAG_DATA.WS_L_TELLER);
        IBS.init(SCCGWA, BPCOIEC);
        BPCOIEC.CCY1 = WS_MPAG_DATA.WS_L_CCY_PAIR.WS_L_CCY;
        BPCOIEC.CCY2 = WS_MPAG_DATA.WS_L_CCY_PAIR.WS_L_CORR_CCY;
        CEP.TRC(SCCGWA, "HERE2");
        CEP.TRC(SCCGWA, BPRTQPH.KEY.EXR_TYP);
        BPCOIEC.EXR_TYP = BPRTQPH.KEY.EXR_TYP;
        S000_CALL_BPZSIEC();
        if (pgmRtn) return;
        if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
        JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
        if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
        JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
        if (BPCOIEC.EXR_CODE.substring(0, 3).trim().length() > 0 
            && BPCOIEC.EXR_CODE.substring(4 - 1, 4 + 3 - 1).trim().length() > 0) {
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            WS_MPAG_DATA.WS_L_CCY_PAIR.WS_L_CCY = BPCOIEC.EXR_CODE.substring(0, 3);
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            WS_MPAG_DATA.WS_L_CCY_PAIR.WS_L_CORR_CCY = BPCOIEC.EXR_CODE.substring(4 - 1, 4 + 3 - 1);
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_MPAG_DATA);
            SCCMPAG.DATA_LEN = 194;
            B_MPAG();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "NOTOUTPUT");
        }
        CEP.TRC(SCCGWA, "OUTPUT");
        CEP.TRC(SCCGWA, WS_MPAG_DATA.WS_L_CCY_PAIR.WS_L_CCY);
        CEP.TRC(SCCGWA, WS_MPAG_DATA.WS_L_CCY_PAIR.WS_L_CORR_CCY);
    }
    public void C000_OUT_TQP_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_MPAG_DATA);
        WS_MPAG_DATA.WS_L_BR = BPRTQP.KEY.BR;
        WS_MPAG_DATA.WS_L_EXR_TYP = BPRTQP.KEY.EXR_TYP;
        WS_MPAG_DATA.WS_L_CCY_PAIR.WS_L_CCY = BPRTQP.KEY.CCY;
        WS_MPAG_DATA.WS_L_CCY_PAIR.WS_L_CORR_CCY = BPRTQP.KEY.CORR_CCY;
        WS_MPAG_DATA.WS_L_EFF_DATE = BPRTQP.KEY.EFF_DT;
        WS_MPAG_DATA.WS_L_EFF_TIME = BPRTQP.KEY.EFF_TM;
        WS_MPAG_DATA.WS_L_EXP_DATE = 99991231;
        WS_MPAG_DATA.WS_L_EXP_TIME = 235959;
        WS_MPAG_DATA.WS_L_MID = BPRTQP.LOC_MID;
        WS_MPAG_DATA.WS_L_CCS_BUY = BPRTQP.CCS_BUY;
        WS_MPAG_DATA.WS_L_CCS_SELL = BPRTQP.CCS_SELL;
        WS_MPAG_DATA.WS_L_CFX_BUY = BPRTQP.CFX_BUY;
        WS_MPAG_DATA.WS_L_CFX_SELL = BPRTQP.CFX_SELL;
        WS_MPAG_DATA.WS_L_CS_BUY = BPRTQP.CS_BUY;
        WS_MPAG_DATA.WS_L_CS_SELL = BPRTQP.CS_SELL;
        WS_MPAG_DATA.WS_L_FX_BUY = BPRTQP.FX_BUY;
        WS_MPAG_DATA.WS_L_FX_SELL = BPRTQP.FX_SELL;
        WS_MPAG_DATA.WS_L_CRE_DT = BPRTQP.UPD_DT;
        WS_MPAG_DATA.WS_L_TELLER = BPRTQP.UPD_TLR;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_MPAG_DATA);
        SCCMPAG.DATA_LEN = 194;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_CHK_DATE_TIME() throws IOException,SQLException,Exception {
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_R_S_DT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_I_S_DT);
        JIBS_tmp_str[4] = IBS.CLS2CPY(SCCGWA, WS_R_E_DT);
        JIBS_tmp_str[3] = IBS.CLS2CPY(SCCGWA, WS_I_S_DT);
        JIBS_tmp_str[6] = IBS.CLS2CPY(SCCGWA, WS_R_S_DT);
        JIBS_tmp_str[5] = IBS.CLS2CPY(SCCGWA, WS_I_S_DT);
        JIBS_tmp_str[9] = IBS.CLS2CPY(SCCGWA, WS_R_E_DT);
        JIBS_tmp_str[8] = IBS.CLS2CPY(SCCGWA, WS_I_E_DT);
        JIBS_tmp_str[11] = IBS.CLS2CPY(SCCGWA, WS_R_S_DT);
        JIBS_tmp_str[10] = IBS.CLS2CPY(SCCGWA, WS_I_E_DT);
        JIBS_tmp_str[14] = IBS.CLS2CPY(SCCGWA, WS_R_E_DT);
        JIBS_tmp_str[13] = IBS.CLS2CPY(SCCGWA, WS_I_E_DT);
        if ((JIBS_tmp_str[1].compareTo(JIBS_tmp_str[0]) <= 0 
            && JIBS_tmp_str[4].compareTo(JIBS_tmp_str[3]) >= 0) 
            || (JIBS_tmp_str[6].compareTo(JIBS_tmp_str[5]) >= 0 
            && JIBS_tmp_str[9].compareTo(JIBS_tmp_str[8]) <= 0) 
            || (JIBS_tmp_str[11].compareTo(JIBS_tmp_str[10]) <= 0 
            && JIBS_tmp_str[14].compareTo(JIBS_tmp_str[13]) >= 0)) {
            WS_OUT_REC_FLG = 'Y';
        }
    }
    public void R000_GET_UNIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRD);
        IBS.init(SCCGWA, BPCTEXRM);
        BPREXRD.KEY.EXR_TYP = BPCQQTP.EXR_TYP;
        BPREXRD.KEY.FWD_TENOR = BPCQQTP.FWD_TENOR;
        BPREXRD.KEY.CCY = BPRTQPH.KEY.CCY;
        BPREXRD.KEY.CORR_CCY = BPRTQPH.KEY.CORR_CCY;
        BPCTEXRM.INFO.FUNC = 'Q';
        BPCTEXRM.QUERY_OPTION.DBL_CHK_FLG = 'Y';
        S000_CALL_BPZTEXRM();
        if (pgmRtn) return;
        if (BPCTEXRM.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EX_RATE_UNDEFINE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_FWD_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_FWD_TENOR;
        BPCOQPCD.INPUT_DATA.CODE = WS_FWD_TENOR;
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_CODE, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_DATE;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            if (WS_ERR_MSG == null) WS_ERR_MSG = "";
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_ERR_MSG += " ";
            WS_ERR_MSG = "SC" + WS_ERR_MSG.substring(2);
            if (WS_ERR_MSG == null) WS_ERR_MSG = "";
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_ERR_MSG += " ";
            JIBS_tmp_str[0] = "" + SCCCKDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_ERR_MSG = WS_ERR_MSG.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_ERR_MSG.substring(3 + 4 - 1);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTTQPH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTQPH.KEY.EXP_DT);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.EXP_TM);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.FWD_TENOR);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.BR);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.CCY);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.CORR_CCY);
        BPCRTQPH.INFO.POINTER = BPRTQPH;
        BPCRTQPH.INFO.LEN = 412;
        IBS.CALLCPN(SCCGWA, CPN_R_TQPH, BPCRTQPH);
    }
    public void S000_CALL_BPZTEXRM() throws IOException,SQLException,Exception {
        BPCTEXRM.POINTER = BPREXRD;
        BPCTEXRM.LEN = 259;
        IBS.CALLCPN(SCCGWA, CPN_R_EXRM, BPCTEXRM);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void S000_CALL_BPZSIEC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCQQTP.CCY);
        CEP.TRC(SCCGWA, BPCQQTP.CORR_CCY);
        IBS.CALLCPN(SCCGWA, CPN_INQ_EXR_CODE, BPCOIEC);
        CEP.TRC(SCCGWA, BPCOIEC);
    }
    public void S000_CALL_BPZSITQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_EXR_RATE, BPCITQ);
        if (BPCITQ.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCITQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
