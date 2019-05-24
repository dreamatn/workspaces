package com.hisun.DD;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSMREG {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm DDTREGB_BR = new brParm();
    DBParm DDTREGB_RD;
    DBParm DDTREGM_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD881";
    String WS_REP_NO = " ";
    int WS_AC_DATE = 0;
    long WS_JRN_NO = 0;
    char WS_REGB_FLG = ' ';
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCOREGA DDCOREGA = new DDCOREGA();
    DDCOREGB DDCOREGB = new DDCOREGB();
    DDCOREGC DDCOREGC = new DDCOREGC();
    DDCOREGM DDCOREGM = new DDCOREGM();
    DDRMST DDRMST = new DDRMST();
    DDRREGB DDRREGB = new DDRREGB();
    DDRREGM DDRREGM = new DDRREGM();
    SCCGWA SCCGWA;
    DDCSMREG DDCSMREG;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, DDCSMREG DDCSMREG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSMREG = DDCSMREG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSMREG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (DDCSMREG.FUNC == 'B') {
            B200_BRW_REG_REC_PROC();
            if (pgmRtn) return;
        } else if (DDCSMREG.FUNC == 'I') {
            B300_INQ_REG_REC_PROC();
            if (pgmRtn) return;
        } else if (DDCSMREG.FUNC == 'A') {
            B400_ADD_REG_REC_PROC();
            if (pgmRtn) return;
        } else if (DDCSMREG.FUNC == 'M') {
            B500_MOD_REG_REC_PROC();
            if (pgmRtn) return;
        } else if (DDCSMREG.FUNC == 'D') {
            B600_DEL_REG_REC_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID TX TYPE(" + DDCSMREG.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSMREG.FUNC);
        CEP.TRC(SCCGWA, DDCSMREG.REP_NO);
        CEP.TRC(SCCGWA, DDCSMREG.REP_TYP);
        CEP.TRC(SCCGWA, DDCSMREG.BASE_FLG);
        CEP.TRC(SCCGWA, DDCSMREG.REP_FLG);
        CEP.TRC(SCCGWA, DDCSMREG.MAN_FLG);
        if (DDCSMREG.FUNC == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDCSMREG.FUNC != 'B' 
            && DDCSMREG.FUNC != 'I' 
            && DDCSMREG.FUNC != 'A' 
            && DDCSMREG.FUNC != 'M' 
            && DDCSMREG.FUNC != 'D') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_FUNC_INVALID;
            if (DDCSMREG.FUNC == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+DDCSMREG.FUNC);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if ((DDCSMREG.FUNC == 'I' 
            || DDCSMREG.FUNC == 'M' 
            || DDCSMREG.FUNC == 'D') 
            && DDCSMREG.REP_NO.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_REP_NO_M_INPUT;
            if (DDCSMREG.REP_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDCSMREG.REP_NO);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if ((DDCSMREG.FUNC == 'A' 
            || DDCSMREG.FUNC == 'M' 
            || DDCSMREG.FUNC == 'D') 
            && (DDCSMREG.BASE_FLG == ' ' 
            || DDCSMREG.REP_FLG == ' ' 
            || DDCSMREG.MAN_FLG == ' ')) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_TABLE_FLG_M_INPUT;
            if (DDCSMREG.BASE_FLG == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+DDCSMREG.BASE_FLG);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_MSGID = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B200_BRW_REG_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSMREG.REP_NO);
        CEP.TRC(SCCGWA, DDCSMREG.JRN_NO);
        CEP.TRC(SCCGWA, DDCSMREG.TR_AC);
        IBS.init(SCCGWA, DDRREGB);
        WS_REGB_FLG = 'N';
        if (DDCSMREG.REP_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "BRW BY REP NO");
            DDRREGB.KEY.REP_NO = DDCSMREG.REP_NO;
            R000_READ_DDTREGB();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_REGB_REC_NOTFND);
            } else {
                WS_REGB_FLG = 'Y';
                B210_GET_DDTREGM_INF();
                if (pgmRtn) return;
                B220_BEGIN_MPAGE_OUTPUT();
                if (pgmRtn) return;
                B230_OUTPUT_DATA_BRW();
                if (pgmRtn) return;
                B800_OUTPUT_DATA();
                if (pgmRtn) return;
            }
        }
        if ((DDCSMREG.JRN_NO != 0 
            && DDCSMREG.JRN_NO != 0X00 
            && DDCSMREG.JRN_NO != ' ') 
            && WS_REGB_FLG == 'N') {
            CEP.TRC(SCCGWA, "BRW BY JRNNO");
            DDRREGB.JRN_NO = DDCSMREG.JRN_NO;
            R000_STARTBR_DDTREGB();
            if (pgmRtn) return;
            R000_READNEXT_DDTREGB();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_REGB_REC_NOTFND);
            } else {
                WS_REGB_FLG = 'Y';
                B220_BEGIN_MPAGE_OUTPUT();
                if (pgmRtn) return;
                while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                    B210_GET_DDTREGM_INF();
                    if (pgmRtn) return;
                    B230_OUTPUT_DATA_BRW();
                    if (pgmRtn) return;
                    B800_OUTPUT_DATA();
                    if (pgmRtn) return;
                    R000_READNEXT_DDTREGB();
                    if (pgmRtn) return;
                }
                R000_ENDBR_DDTREGB();
                if (pgmRtn) return;
            }
        }
        if (DDCSMREG.TR_AC.trim().length() > 0 
            && WS_REGB_FLG == 'N') {
            CEP.TRC(SCCGWA, "BRW BY TRAC");
            DDRREGB.TR_AC = DDCSMREG.TR_AC;
            R100_STARTBR_DDTREGB();
            if (pgmRtn) return;
            R100_READNEXT_DDTREGB();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_REGB_REC_NOTFND);
            } else {
                B220_BEGIN_MPAGE_OUTPUT();
                if (pgmRtn) return;
                while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                    B210_GET_DDTREGM_INF();
                    if (pgmRtn) return;
                    B230_OUTPUT_DATA_BRW();
                    if (pgmRtn) return;
                    B800_OUTPUT_DATA();
                    if (pgmRtn) return;
                    R100_READNEXT_DDTREGB();
                    if (pgmRtn) return;
                }
                R100_ENDBR_DDTREGB();
                if (pgmRtn) return;
            }
        }
    }
    public void B300_INQ_REG_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRREGB);
        DDRREGB.KEY.REP_NO = DDCSMREG.REP_NO;
        R000_READ_DDTREGB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_REGB_REC_NOTFND);
        }
        IBS.init(SCCGWA, DDRREGM);
        DDRREGM.KEY.REP_NO = DDCSMREG.REP_NO;
        R000_READ_DDTREGM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_REGM_REC_NOTFND);
        }
        DDCSMREG.BASE_FLG = 'Y';
        DDCSMREG.REP_FLG = 'Y';
        DDCSMREG.MAN_FLG = 'Y';
        B700_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B400_ADD_REG_REC_PROC() throws IOException,SQLException,Exception {
        B410_GEN_REP_NO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSMREG.BASE_FLG);
        CEP.TRC(SCCGWA, DDCSMREG.REP_FLG);
        CEP.TRC(SCCGWA, DDCSMREG.MAN_FLG);
        if (DDCSMREG.BASE_FLG == 'Y') {
            IBS.init(SCCGWA, DDRREGB);
            DDRREGB.KEY.REP_NO = WS_REP_NO;
            DDRREGB.REP_TYP = DDCSMREG.REP_TYP;
            DDRREGB.OPR_TYP = 'A';
            DDRREGB.OPR_RSN = DDCSMREG.OPR_RSN;
            DDRREGB.JRN_NO = WS_JRN_NO;
            DDRREGB.TR_AC = DDCSMREG.TR_AC;
            DDRREGB.CI_TYP = DDCSMREG.CI_TYP;
            DDRREGB.CI_ID_NO = DDCSMREG.CI_ID_NO;
            DDRREGB.ORG_CD = DDCSMREG.ORG_CD;
            DDRREGB.CI_NM = DDCSMREG.CI_NM;
            DDRREGB.TO_CI_NM = DDCSMREG.TO_CI_NM;
            DDRREGB.CCY = DDCSMREG.CCY;
            DDRREGB.TR_AMT = DDCSMREG.TR_AMT;
            DDRREGB.EX_RATE = DDCSMREG.EX_RATE;
            DDRREGB.EX_AMT = DDCSMREG.EX_AMT;
            DDRREGB.CHN_ACNO = DDCSMREG.CHN_ACNO;
            DDRREGB.CASH_AMT = DDCSMREG.CASH_AMT;
            DDRREGB.FOR_ACNO = DDCSMREG.FOR_ACNO;
            DDRREGB.OTH_AMT = DDCSMREG.OTH_AMT;
            DDRREGB.OTH_ACNO = DDCSMREG.OTH_ACNO;
            DDRREGB.PAY_MTH = DDCSMREG.PAY_MTH;
            DDRREGB.CHN_FEE_CCY = DDCSMREG.CN_F_CCY;
            DDRREGB.CHN_FEE_AMT = DDCSMREG.CN_F_AMT;
            DDRREGB.FOR_FEE_CCY = DDCSMREG.FO_F_CCY;
            DDRREGB.FOR_FEE_AMT = DDCSMREG.FO_F_AMT;
            DDRREGB.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRREGB.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRREGB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRREGB.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, DDRREGB.REP_TYP);
            CEP.TRC(SCCGWA, DDRREGB.OPR_TYP);
            CEP.TRC(SCCGWA, DDRREGB.OPR_RSN);
            CEP.TRC(SCCGWA, DDRREGB.JRN_NO);
            CEP.TRC(SCCGWA, DDRREGB.TR_AC);
            CEP.TRC(SCCGWA, DDRREGB.CI_TYP);
            CEP.TRC(SCCGWA, DDRREGB.CI_ID_NO);
            CEP.TRC(SCCGWA, DDRREGB.ORG_CD);
            CEP.TRC(SCCGWA, DDRREGB.CI_NM);
            CEP.TRC(SCCGWA, DDRREGB.TO_CI_NM);
            CEP.TRC(SCCGWA, DDRREGB.CCY);
            CEP.TRC(SCCGWA, DDRREGB.TR_AMT);
            CEP.TRC(SCCGWA, DDRREGB.EX_RATE);
            CEP.TRC(SCCGWA, DDRREGB.EX_AMT);
            CEP.TRC(SCCGWA, DDRREGB.CHN_ACNO);
            CEP.TRC(SCCGWA, DDRREGB.CASH_AMT);
            CEP.TRC(SCCGWA, DDRREGB.FOR_ACNO);
            CEP.TRC(SCCGWA, DDRREGB.OTH_AMT);
            CEP.TRC(SCCGWA, DDRREGB.OTH_ACNO);
            CEP.TRC(SCCGWA, DDRREGB.PAY_MTH);
            CEP.TRC(SCCGWA, DDRREGB.CHN_FEE_CCY);
            CEP.TRC(SCCGWA, DDRREGB.CHN_FEE_AMT);
            CEP.TRC(SCCGWA, DDRREGB.FOR_FEE_CCY);
            CEP.TRC(SCCGWA, DDRREGB.FOR_FEE_AMT);
            CEP.TRC(SCCGWA, DDRREGB.CRT_DATE);
            CEP.TRC(SCCGWA, DDRREGB.CRT_TLR);
            CEP.TRC(SCCGWA, DDRREGB.UPDTBL_DATE);
            CEP.TRC(SCCGWA, DDRREGB.UPDTBL_TLR);
            CEP.TRC(SCCGWA, "WRITE DDTREGB");
            R000_WRITE_DDTREGB();
            if (pgmRtn) return;
        }
        if (DDCSMREG.REP_FLG == 'Y' 
            || DDCSMREG.MAN_FLG == 'Y') {
            DDRREGM.KEY.REP_NO = WS_REP_NO;
            DDRREGM.REP_TYP = DDCSMREG.REP_TYP;
            DDRREGM.OPR_TYP = 'A';
            DDRREGM.OPR_RSN = DDCSMREG.OPR_RSN;
            DDRREGM.MOD_FLG = DDCSMREG.MAN_FLG;
            DDRREGM.REG_FLG = DDCSMREG.REP_FLG;
            DDRREGM.CNTY_CD = DDCSMREG.CNTY_CD;
            DDRREGM.PAY_TYP = DDCSMREG.PAY_TYP;
            DDRREGM.TX_CODE1 = DDCSMREG.TX_CODE1;
            DDRREGM.AMT1 = DDCSMREG.AMT1;
            DDRREGM.REMARKS1 = DDCSMREG.REMARKS1;
            DDRREGM.TX_CODE2 = DDCSMREG.TX_CODE2;
            DDRREGM.AMT2 = DDCSMREG.AMT2;
            DDRREGM.REMARKS2 = DDCSMREG.REMARKS2;
            DDRREGM.REF_FLG = DDCSMREG.REF_FLG;
            DDRREGM.REF_NO = DDCSMREG.REF_NO;
            DDRREGM.PAY_ATTR = DDCSMREG.PAY_ATTR;
            DDRREGM.REF_CHK_NO = DDCSMREG.REF_CKNO;
            DDRREGM.CHK_AMT = DDCSMREG.CHK_AMT;
            DDRREGM.IMP_DATE = DDCSMREG.IMP_DATE;
            DDRREGM.CONTR_NO = DDCSMREG.CONTR_NO;
            DDRREGM.INV_NO = DDCSMREG.INV_NO;
            DDRREGM.CUSM_CD = DDCSMREG.CUSM_CD;
            DDRREGM.CUSM_NO = DDCSMREG.CUSM_NO;
            DDRREGM.CUSM_CCY = DDCSMREG.CUSM_CCY;
            DDRREGM.CUSM_AMT = DDCSMREG.CUSM_AMT;
            DDRREGM.OFF_AMT = DDCSMREG.OFF_AMT;
            DDRREGM.CUSM_CNM = DDCSMREG.CUSM_CNM;
            DDRREGM.CUSM_TEL = DDCSMREG.CUSM_TEL;
            DDRREGM.CUSM_DATE = DDCSMREG.CUSM_DT;
            DDRREGM.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRREGM.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRREGM.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRREGM.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, DDRREGM.KEY.REP_NO);
            CEP.TRC(SCCGWA, DDRREGM.REP_TYP);
            CEP.TRC(SCCGWA, DDRREGM.OPR_TYP);
            CEP.TRC(SCCGWA, DDRREGM.OPR_RSN);
            CEP.TRC(SCCGWA, DDRREGM.MOD_FLG);
            CEP.TRC(SCCGWA, DDRREGM.REG_FLG);
            CEP.TRC(SCCGWA, DDRREGM.CNTY_CD);
            CEP.TRC(SCCGWA, DDRREGM.PAY_TYP);
            CEP.TRC(SCCGWA, DDRREGM.TX_CODE1);
            CEP.TRC(SCCGWA, DDRREGM.AMT1);
            CEP.TRC(SCCGWA, DDRREGM.REMARKS1);
            CEP.TRC(SCCGWA, DDRREGM.TX_CODE2);
            CEP.TRC(SCCGWA, DDRREGM.AMT2);
            CEP.TRC(SCCGWA, DDRREGM.REMARKS2);
            CEP.TRC(SCCGWA, DDRREGM.REF_FLG);
            CEP.TRC(SCCGWA, DDRREGM.REF_NO);
            CEP.TRC(SCCGWA, DDRREGM.PAY_ATTR);
            CEP.TRC(SCCGWA, DDRREGM.REF_CHK_NO);
            CEP.TRC(SCCGWA, DDRREGM.CHK_AMT);
            CEP.TRC(SCCGWA, DDRREGM.IMP_DATE);
            CEP.TRC(SCCGWA, DDRREGM.CONTR_NO);
            CEP.TRC(SCCGWA, DDRREGM.INV_NO);
            CEP.TRC(SCCGWA, DDRREGM.CUSM_CD);
            CEP.TRC(SCCGWA, DDRREGM.CUSM_NO);
            CEP.TRC(SCCGWA, DDRREGM.CUSM_CCY);
            CEP.TRC(SCCGWA, DDRREGM.CUSM_AMT);
            CEP.TRC(SCCGWA, DDRREGM.OFF_AMT);
            CEP.TRC(SCCGWA, DDRREGM.CUSM_CNM);
            CEP.TRC(SCCGWA, DDRREGM.CUSM_TEL);
            CEP.TRC(SCCGWA, DDRREGM.CUSM_DATE);
            CEP.TRC(SCCGWA, DDRREGM.CRT_DATE);
            CEP.TRC(SCCGWA, DDRREGM.CRT_TLR);
            CEP.TRC(SCCGWA, DDRREGM.UPDTBL_DATE);
            CEP.TRC(SCCGWA, DDRREGM.UPDTBL_TLR);
            CEP.TRC(SCCGWA, "WRITE DDTREGM");
            R000_WRITE_DDTREGM();
            if (pgmRtn) return;
        }
        B700_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B410_GEN_REP_NO() throws IOException,SQLException,Exception {
        if (WS_REP_NO == null) WS_REP_NO = "";
        JIBS_tmp_int = WS_REP_NO.length();
        for (int i=0;i<22-JIBS_tmp_int;i++) WS_REP_NO += " ";
        WS_REP_NO = "310001" + WS_REP_NO.substring(6);
        if (WS_REP_NO == null) WS_REP_NO = "";
        JIBS_tmp_int = WS_REP_NO.length();
        for (int i=0;i<22-JIBS_tmp_int;i++) WS_REP_NO += " ";
        WS_REP_NO = WS_REP_NO.substring(0, 7 - 1) + "0043" + WS_REP_NO.substring(7 + 4 - 1);
        if (WS_REP_NO == null) WS_REP_NO = "";
        JIBS_tmp_int = WS_REP_NO.length();
        for (int i=0;i<22-JIBS_tmp_int;i++) WS_REP_NO += " ";
        WS_REP_NO = WS_REP_NO.substring(0, 11 - 1) + "01" + WS_REP_NO.substring(11 + 2 - 1);
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, WS_AC_DATE);
        CEP.TRC(SCCGWA, WS_JRN_NO);
        JIBS_tmp_str[0] = "" + WS_AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (WS_REP_NO == null) WS_REP_NO = "";
        JIBS_tmp_int = WS_REP_NO.length();
        for (int i=0;i<22-JIBS_tmp_int;i++) WS_REP_NO += " ";
        WS_REP_NO = WS_REP_NO.substring(0, 13 - 1) + JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1) + WS_REP_NO.substring(13 + 6 - 1);
        JIBS_tmp_str[0] = "" + WS_JRN_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (WS_REP_NO == null) WS_REP_NO = "";
        JIBS_tmp_int = WS_REP_NO.length();
        for (int i=0;i<22-JIBS_tmp_int;i++) WS_REP_NO += " ";
        WS_REP_NO = WS_REP_NO.substring(0, 19 - 1) + JIBS_tmp_str[0].substring(9 - 1, 9 + 4 - 1) + WS_REP_NO.substring(19 + 4 - 1);
        CEP.TRC(SCCGWA, WS_REP_NO);
    }
    public void B500_MOD_REG_REC_PROC() throws IOException,SQLException,Exception {
        if (DDCSMREG.BASE_FLG == 'Y') {
            IBS.init(SCCGWA, DDRREGB);
            DDRREGB.KEY.REP_NO = DDCSMREG.REP_NO;
            R000_READ_UPDATE_DDTREGB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_REGB_REC_NOTFND);
            }
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                DDRREGB.REP_TYP = DDCSMREG.REP_TYP;
                DDRREGB.OPR_TYP = 'C';
                DDRREGB.OPR_RSN = DDCSMREG.OPR_RSN;
                DDRREGB.CI_TYP = DDCSMREG.CI_TYP;
                DDRREGB.CI_ID_NO = DDCSMREG.CI_ID_NO;
                DDRREGB.ORG_CD = DDCSMREG.ORG_CD;
                DDRREGB.CI_NM = DDCSMREG.CI_NM;
                DDRREGB.TO_CI_NM = DDCSMREG.TO_CI_NM;
                DDRREGB.CCY = DDCSMREG.CCY;
                DDRREGB.TR_AMT = DDCSMREG.TR_AMT;
                DDRREGB.EX_RATE = DDCSMREG.EX_RATE;
                DDRREGB.EX_AMT = DDCSMREG.EX_AMT;
                DDRREGB.CHN_ACNO = DDCSMREG.CHN_ACNO;
                DDRREGB.CASH_AMT = DDCSMREG.CASH_AMT;
                DDRREGB.FOR_ACNO = DDCSMREG.FOR_ACNO;
                DDRREGB.OTH_AMT = DDCSMREG.OTH_AMT;
                DDRREGB.OTH_ACNO = DDCSMREG.OTH_ACNO;
                DDRREGB.PAY_MTH = DDCSMREG.PAY_MTH;
                DDRREGB.CHN_FEE_CCY = DDCSMREG.CN_F_CCY;
                DDRREGB.CHN_FEE_AMT = DDCSMREG.CN_F_AMT;
                DDRREGB.FOR_FEE_CCY = DDCSMREG.FO_F_CCY;
                DDRREGB.FOR_FEE_AMT = DDCSMREG.FO_F_AMT;
                DDRREGB.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRREGB.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                DDRREGB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRREGB.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                R000_REWRITE_DDTREGB();
                if (pgmRtn) return;
            }
        }
        if (DDCSMREG.REP_FLG == 'Y' 
            || DDCSMREG.MAN_FLG == 'Y') {
            IBS.init(SCCGWA, DDRREGM);
            DDRREGM.KEY.REP_NO = DDCSMREG.REP_NO;
            R000_READ_UPDATE_DDTREGM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_REGM_REC_NOTFND);
            }
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                DDRREGM.REP_TYP = DDCSMREG.REP_TYP;
                DDRREGM.OPR_TYP = 'C';
                DDRREGM.OPR_RSN = DDCSMREG.OPR_RSN;
                DDRREGM.MOD_FLG = DDCSMREG.MAN_FLG;
                DDRREGM.REG_FLG = DDCSMREG.REP_FLG;
                DDRREGM.CNTY_CD = DDCSMREG.CNTY_CD;
                DDRREGM.PAY_TYP = DDCSMREG.PAY_TYP;
                DDRREGM.TX_CODE1 = DDCSMREG.TX_CODE1;
                DDRREGM.AMT1 = DDCSMREG.AMT1;
                DDRREGM.REMARKS1 = DDCSMREG.REMARKS1;
                DDRREGM.TX_CODE2 = DDCSMREG.TX_CODE2;
                DDRREGM.AMT2 = DDCSMREG.AMT2;
                DDRREGM.REMARKS2 = DDCSMREG.REMARKS2;
                DDRREGM.REF_FLG = DDCSMREG.REF_FLG;
                DDRREGM.REF_NO = DDCSMREG.REF_NO;
                DDRREGM.PAY_ATTR = DDCSMREG.PAY_ATTR;
                DDRREGM.REF_CHK_NO = DDCSMREG.REF_CKNO;
                DDRREGM.CHK_AMT = DDCSMREG.CHK_AMT;
                DDRREGM.IMP_DATE = DDCSMREG.IMP_DATE;
                DDRREGM.CONTR_NO = DDCSMREG.CONTR_NO;
                DDRREGM.INV_NO = DDCSMREG.INV_NO;
                DDRREGM.CUSM_CD = DDCSMREG.CUSM_CD;
                DDRREGM.CUSM_NO = DDCSMREG.CUSM_NO;
                DDRREGM.CUSM_CCY = DDCSMREG.CUSM_CCY;
                DDRREGM.CUSM_AMT = DDCSMREG.CUSM_AMT;
                DDRREGM.OFF_AMT = DDCSMREG.OFF_AMT;
                DDRREGM.CUSM_CNM = DDCSMREG.CUSM_CNM;
                DDRREGM.CUSM_TEL = DDCSMREG.CUSM_TEL;
                DDRREGM.CUSM_DATE = DDCSMREG.CUSM_DT;
                DDRREGM.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRREGM.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                DDRREGM.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRREGM.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                R000_REWRITE_DDTREGM();
                if (pgmRtn) return;
            }
        }
        B700_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B600_DEL_REG_REC_PROC() throws IOException,SQLException,Exception {
        if (DDCSMREG.BASE_FLG == 'Y') {
            IBS.init(SCCGWA, DDRREGB);
            DDRREGB.KEY.REP_NO = DDCSMREG.REP_NO;
            R000_READ_UPDATE_DDTREGB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_REGB_REC_NOTFND);
            }
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                DDRREGB.OPR_TYP = 'D';
                DDRREGB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRREGB.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                R000_REWRITE_DDTREGB();
                if (pgmRtn) return;
            }
        }
        if (DDCSMREG.REP_FLG == 'Y' 
            || DDCSMREG.MAN_FLG == 'Y') {
            IBS.init(SCCGWA, DDRREGM);
            DDRREGM.KEY.REP_NO = DDCSMREG.REP_NO;
            R000_READ_UPDATE_DDTREGM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_REGM_REC_NOTFND);
            }
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                DDRREGM.OPR_TYP = 'D';
                DDRREGM.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRREGM.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                R000_REWRITE_DDTREGM();
                if (pgmRtn) return;
            }
        }
        B700_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B210_GET_DDTREGM_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRREGB.KEY.REP_NO);
        IBS.init(SCCGWA, DDRREGM);
        DDRREGM.KEY.REP_NO = DDRREGB.KEY.REP_NO;
        R000_READ_DDTREGM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_REGB_REC_NOTFND);
        }
    }
    public void R000_STARTBR_DDTREGB() throws IOException,SQLException,Exception {
        DDTREGB_BR.rp = new DBParm();
        DDTREGB_BR.rp.TableName = "DDTREGB";
        DDTREGB_BR.rp.where = "JRN_NO = :DDRREGB.JRN_NO";
        IBS.STARTBR(SCCGWA, DDRREGB, this, DDTREGB_BR);
    }
    public void R000_READNEXT_DDTREGB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRREGB, this, DDTREGB_BR);
    }
    public void R000_ENDBR_DDTREGB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTREGB_BR);
    }
    public void R100_STARTBR_DDTREGB() throws IOException,SQLException,Exception {
        DDTREGB_BR.rp = new DBParm();
        DDTREGB_BR.rp.TableName = "DDTREGB";
        DDTREGB_BR.rp.where = "TR_AC = :DDRREGB.TR_AC";
        IBS.STARTBR(SCCGWA, DDRREGB, this, DDTREGB_BR);
    }
    public void R100_READNEXT_DDTREGB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRREGB, this, DDTREGB_BR);
    }
    public void R100_ENDBR_DDTREGB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTREGB_BR);
    }
    public void R000_REWRITE_DDTREGB() throws IOException,SQLException,Exception {
        DDTREGB_RD = new DBParm();
        DDTREGB_RD.TableName = "DDTREGB";
        IBS.REWRITE(SCCGWA, DDRREGB, DDTREGB_RD);
    }
    public void R000_REWRITE_DDTREGM() throws IOException,SQLException,Exception {
        DDTREGM_RD = new DBParm();
        DDTREGM_RD.TableName = "DDTREGM";
        IBS.REWRITE(SCCGWA, DDRREGM, DDTREGM_RD);
    }
    public void R000_READ_UPDATE_DDTREGB() throws IOException,SQLException,Exception {
        DDTREGB_RD = new DBParm();
        DDTREGB_RD.TableName = "DDTREGB";
        DDTREGB_RD.where = "REP_NO = :DDRREGB.KEY.REP_NO";
        DDTREGB_RD.upd = true;
        IBS.READ(SCCGWA, DDRREGB, this, DDTREGB_RD);
    }
    public void R000_READ_UPDATE_DDTREGM() throws IOException,SQLException,Exception {
        DDTREGM_RD = new DBParm();
        DDTREGM_RD.TableName = "DDTREGM";
        DDTREGM_RD.where = "REP_NO = :DDRREGM.KEY.REP_NO";
        DDTREGM_RD.upd = true;
        IBS.READ(SCCGWA, DDRREGM, this, DDTREGM_RD);
    }
    public void R000_WRITE_DDTREGB() throws IOException,SQLException,Exception {
        DDTREGB_RD = new DBParm();
        DDTREGB_RD.TableName = "DDTREGB";
        IBS.WRITE(SCCGWA, DDRREGB, DDTREGB_RD);
    }
    public void R000_WRITE_DDTREGM() throws IOException,SQLException,Exception {
        DDTREGM_RD = new DBParm();
        DDTREGM_RD.TableName = "DDTREGM";
        IBS.WRITE(SCCGWA, DDRREGM, DDTREGM_RD);
    }
    public void R000_READ_DDTREGB() throws IOException,SQLException,Exception {
        DDTREGB_RD = new DBParm();
        DDTREGB_RD.TableName = "DDTREGB";
        DDTREGB_RD.where = "REP_NO = :DDRREGB.KEY.REP_NO";
        IBS.READ(SCCGWA, DDRREGB, this, DDTREGB_RD);
    }
    public void R000_READ_DDTREGM() throws IOException,SQLException,Exception {
        DDTREGM_RD = new DBParm();
        DDTREGM_RD.TableName = "DDTREGM";
        DDTREGM_RD.where = "REP_NO = :DDRREGM.KEY.REP_NO";
        IBS.READ(SCCGWA, DDRREGM, this, DDTREGM_RD);
    }
    public void B220_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 879;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B230_OUTPUT_DATA_BRW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOREGC);
        DDCOREGC.REP_NO = DDRREGB.KEY.REP_NO;
        DDCOREGC.REP_TYP = DDRREGB.REP_TYP;
        DDCOREGC.OPR_TYP = DDRREGB.OPR_TYP;
        DDCOREGC.OPR_RSN = DDRREGB.OPR_RSN;
        DDCOREGC.JRN_NO = DDRREGB.JRN_NO;
        DDCOREGC.TR_AC = DDRREGB.TR_AC;
        DDCOREGC.CI_TYP = DDRREGB.CI_TYP;
        DDCOREGC.CI_ID_NO = DDRREGB.CI_ID_NO;
        DDCOREGC.ORG_CD = DDRREGB.ORG_CD;
        DDCOREGC.CI_NM = DDRREGB.CI_NM;
        DDCOREGC.TO_CI_NM = DDRREGB.TO_CI_NM;
        DDCOREGC.CCY = DDRREGB.CCY;
        DDCOREGC.TR_AMT = DDRREGB.TR_AMT;
        DDCOREGC.CNTY_CD = DDRREGM.CNTY_CD;
        DDCOREGC.PAY_TYP = DDRREGM.PAY_TYP;
        DDCOREGC.TX_CODE1 = DDRREGM.TX_CODE1;
        DDCOREGC.AMT1 = DDRREGM.AMT1;
        CEP.TRC(SCCGWA, DDCOREGC.REP_NO);
        CEP.TRC(SCCGWA, DDCOREGC.REP_TYP);
        CEP.TRC(SCCGWA, DDCOREGC.OPR_TYP);
        CEP.TRC(SCCGWA, DDCOREGC.OPR_RSN);
        CEP.TRC(SCCGWA, DDCOREGC.JRN_NO);
        CEP.TRC(SCCGWA, DDCOREGC.TR_AC);
        CEP.TRC(SCCGWA, DDCOREGC.CI_TYP);
        CEP.TRC(SCCGWA, DDCOREGC.CI_ID_NO);
        CEP.TRC(SCCGWA, DDCOREGC.ORG_CD);
        CEP.TRC(SCCGWA, DDCOREGC.CI_NM);
        CEP.TRC(SCCGWA, DDCOREGC.TO_CI_NM);
        CEP.TRC(SCCGWA, DDCOREGC.CCY);
        CEP.TRC(SCCGWA, DDCOREGC.TR_AMT);
        CEP.TRC(SCCGWA, DDCOREGC.CNTY_CD);
        CEP.TRC(SCCGWA, DDCOREGC.PAY_TYP);
        CEP.TRC(SCCGWA, DDCOREGC.TX_CODE1);
        CEP.TRC(SCCGWA, DDCOREGC.AMT1);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, DDCOREGC);
        SCCMPAG.DATA_LEN = 879;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B700_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOREGA);
        if (DDCSMREG.BASE_FLG == 'Y') {
            DDCOREGA.REP_NO = DDRREGB.KEY.REP_NO;
        }
        if (DDCSMREG.REP_FLG == 'Y' 
            || DDCSMREG.MAN_FLG == 'Y') {
            DDCOREGA.REP_NO = DDRREGM.KEY.REP_NO;
        }
        DDCOREGA.REP_TYP = DDCSMREG.REP_TYP;
        DDCOREGA.OPR_TYP = DDCSMREG.OPR_TYP;
        DDCOREGA.OPR_RSN = DDCSMREG.OPR_RSN;
        DDCOREGA.JRN_NO = DDRREGB.JRN_NO;
        DDCOREGA.TR_AC = DDRREGB.TR_AC;
        DDCOREGA.CI_TYP = DDRREGB.CI_TYP;
        DDCOREGA.CI_ID_NO = DDRREGB.CI_ID_NO;
        DDCOREGA.ORG_CD = DDRREGB.ORG_CD;
        DDCOREGA.CI_NM = DDRREGB.CI_NM;
        DDCOREGA.TO_CI_NM = DDRREGB.TO_CI_NM;
        DDCOREGA.CCY = DDRREGB.CCY;
        DDCOREGA.TR_AMT = DDRREGB.TR_AMT;
        DDCOREGA.EX_RATE = DDRREGB.EX_RATE;
        DDCOREGA.EX_AMT = DDRREGB.EX_AMT;
        DDCOREGA.CHN_ACNO = DDRREGB.CHN_ACNO;
        DDCOREGA.CASH_AMT = DDRREGB.CASH_AMT;
        DDCOREGA.FOR_ACNO = DDRREGB.FOR_ACNO;
        DDCOREGA.OTH_AMT = DDRREGB.OTH_AMT;
        DDCOREGA.OTH_ACNO = DDRREGB.OTH_ACNO;
        DDCOREGA.PAY_MTH = DDRREGB.PAY_MTH;
        DDCOREGA.CN_F_CCY = DDRREGB.CHN_FEE_CCY;
        DDCOREGA.CN_F_AMT = DDRREGB.CHN_FEE_AMT;
        DDCOREGA.FO_F_CCY = DDRREGB.FOR_FEE_CCY;
        DDCOREGA.FO_F_AMT = DDRREGB.FOR_FEE_AMT;
        DDCOREGA.MOD_FLG = DDRREGM.MOD_FLG;
        DDCOREGA.REG_FLG = DDRREGM.REG_FLG;
        DDCOREGA.CNTY_CD = DDRREGM.CNTY_CD;
        DDCOREGA.PAY_TYP = DDRREGM.PAY_TYP;
        DDCOREGA.TX_CODE1 = DDRREGM.TX_CODE1;
        DDCOREGA.AMT1 = DDRREGM.AMT1;
        DDCOREGA.REMARKS1 = DDRREGM.REMARKS1;
        DDCOREGA.TX_CODE2 = DDRREGM.TX_CODE2;
        DDCOREGA.AMT2 = DDRREGM.AMT2;
        DDCOREGA.REMARKS2 = DDRREGM.REMARKS2;
        DDCOREGA.REF_FLG = DDRREGM.REF_FLG;
        DDCOREGA.REF_NO = DDRREGM.REF_NO;
        DDCOREGA.PAY_ATTR = DDRREGM.PAY_ATTR;
        DDCOREGA.REF_CKNO = DDRREGM.REF_CHK_NO;
        DDCOREGA.CHK_AMT = DDRREGM.CHK_AMT;
        DDCOREGA.IMP_DATE = DDRREGM.IMP_DATE;
        DDCOREGA.CONTR_NO = DDRREGM.CONTR_NO;
        DDCOREGA.INV_NO = DDRREGM.INV_NO;
        DDCOREGA.CUSM_CD = DDRREGM.CUSM_CD;
        DDCOREGA.CUSM_NO = DDRREGM.CUSM_NO;
        DDCOREGA.CUSM_CCY = DDRREGM.CUSM_CCY;
        DDCOREGA.CUSM_AMT = DDRREGM.CUSM_AMT;
        DDCOREGA.OFF_AMT = DDRREGM.OFF_AMT;
        DDCOREGA.CUSM_CNM = DDRREGM.CUSM_CNM;
        DDCOREGA.CUSM_TEL = DDRREGM.CUSM_TEL;
        DDCOREGA.CUSM_DATE = DDRREGM.CUSM_DATE;
        CEP.TRC(SCCGWA, "OUTPUT DDTREGB AND DDTREGM");
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOREGA;
        SCCFMT.DATA_LEN = 1716;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B800_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, DDCOREGA);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOREGA;
        SCCFMT.DATA_LEN = 1716;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DDZSMREG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-MOD-REG", DDCSMREG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSGID, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, WS_FLD_NO);
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
