package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICQACRI;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCPRMR;
import com.hisun.SC.SCCSTAR;
import com.hisun.SC.SCRCWAT;
import com.hisun.SO.SORSYS;

public class CMZS9970 {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    short WS_PARM_LEN = 0;
    String WS_FRM_APP = " ";
    CMZS9970_WS_FILE_SEQ WS_FILE_SEQ = new CMZS9970_WS_FILE_SEQ();
    CMZS9970_WS_OUT_FIL_INF WS_OUT_FIL_INF = new CMZS9970_WS_OUT_FIL_INF();
    CMZS9970_WS_PARM WS_PARM = new CMZS9970_WS_PARM();
    CMZS9970_WS_PROC_INFO WS_PROC_INFO = new CMZS9970_WS_PROC_INFO();
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCRBSPC SCRBSPC = new SCRBSPC();
    SCCBSPS SCCBSPS = new SCCBSPS();
    SCCBSP SCCBSP = new SCCBSP();
    SCCPRMR SCCPRMR = new SCCPRMR();
    BPCFITYP BPCFITYP = new BPCFITYP();
    CMCO9970 CMCO9970 = new CMCO9970();
    CMRREQTR CMRREQTR = new CMRREQTR();
    CMCTLIBB CMCTLIBB = new CMCTLIBB();
    CMRTRSTA CMRTRSTA = new CMRTRSTA();
    SORSYS SORSYS = new SORSYS();
    SCRCWA SCRPCWA = new SCRCWA();
    SCRCWAT SCRCWAT = new SCRCWAT();
    SCCSTAR SCCSTAR = new SCCSTAR();
    CICQACRI CICQACRI = new CICQACRI();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCS9970 CMCS9970;
    public void MP(SCCGWA SCCGWA, CMCS9970 CMCS9970) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS9970 = CMCS9970;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZS9970 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS9970.FUNC);
        CEP.TRC(SCCGWA, CMCS9970.BUS_ID);
        CEP.TRC(SCCGWA, CMCS9970.BUS_TYP);
        CEP.TRC(SCCGWA, CMCS9970.BUS_DATE);
        CEP.TRC(SCCGWA, CMCS9970.TRF_TYP);
        CEP.TRC(SCCGWA, CMCS9970.REQ_SYS);
        CEP.TRC(SCCGWA, CMCS9970.BR);
        CEP.TRC(SCCGWA, CMCS9970.DATE_TYP);
        CEP.TRC(SCCGWA, CMCS9970.BEG_DATE);
        CEP.TRC(SCCGWA, CMCS9970.END_DATE);
        CEP.TRC(SCCGWA, CMCS9970.SEQ_TYP);
        CEP.TRC(SCCGWA, CMCS9970.BEG_SEQ);
        CEP.TRC(SCCGWA, CMCS9970.END_SEQ);
        CEP.TRC(SCCGWA, CMCS9970.REV_FLG);
        CEP.TRC(SCCGWA, CMCS9970.CUS_AC);
        CEP.TRC(SCCGWA, CMCS9970.AC_SEQ);
        CEP.TRC(SCCGWA, CMCS9970.CCY);
        CEP.TRC(SCCGWA, CMCS9970.CCY_TYP);
        CEP.TRC(SCCGWA, CMCS9970.DC_FLG);
        CEP.TRC(SCCGWA, CMCS9970.REQ_REF);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_GEN_PARM_DATA();
        if (pgmRtn) return;
        B300_GEN_CMTTRSTA_REC();
        if (pgmRtn) return;
        B400_GEN_CMTREQTR_REC();
        if (pgmRtn) return;
        if (CMCS9970.TRF_TYP == 'T') {
            B500_INQ_DETAIL_PROC();
            if (pgmRtn) return;
        } else {
            B600_SUB_BSP_PROC();
            if (pgmRtn) return;
        }
        B900_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CMCS9970.CUS_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = CMCS9970.CUS_AC;
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            if (CICQACRI.O_DATA.O_FRM_APP_OLD.trim().length() > 0) {
                WS_FRM_APP = CICQACRI.O_DATA.O_FRM_APP_OLD;
            } else {
                WS_FRM_APP = CICQACRI.O_DATA.O_FRM_APP;
            }
        }
        if (CMCS9970.BUS_TYP.trim().length() == 0 
            || CMCS9970.BUS_TYP.charAt(0) == 0X00) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ERR_INPUT_BUS_TYPE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CMCS9970.FUNC == '2' 
            && (CMCS9970.CUS_AC.trim().length() == 0 
            || CMCS9970.CUS_AC.charAt(0) == 0X00)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_AC_NO_MUST_IN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((CMCS9970.FUNC == '1' 
            || CMCS9970.FUNC == '2' 
            || CMCS9970.FUNC == '3' 
            || CMCS9970.FUNC == '4' 
            || CMCS9970.FUNC == '7') 
            && (CMCS9970.CUS_AC.trim().length() == 0 
            || CMCS9970.CUS_AC.charAt(0) == 0X00) 
            && (CMCS9970.REQ_SYS.trim().length() == 0 
            || CMCS9970.REQ_SYS.charAt(0) == 0X00)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_INPUT_REQ_SYS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CMCS9970.DATE_TYP != '1' 
            && CMCS9970.DATE_TYP != '2') {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ERR_DATE_TYPE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CMCS9970.BEG_DATE == 0 
            || CMCS9970.END_DATE == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_INPUT_BE_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CMCS9970.BEG_DATE > CMCS9970.END_DATE) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_BEG_GT_END_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CMCS9970.DATE_TYP == '1' 
            && (CMCS9970.CUS_AC.trim().length() > 0 
            && CMCS9970.CUS_AC.charAt(0) != 0X00) 
            && (!WS_FRM_APP.equalsIgnoreCase("AI"))) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_INQ_TYP1_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((CMCS9970.SEQ_TYP == '1' 
            || CMCS9970.SEQ_TYP == '2') 
            && ((CMCS9970.BEG_SEQ.trim().length() == 0 
            || CMCS9970.BEG_SEQ.charAt(0) == 0X00) 
            || (CMCS9970.END_SEQ.trim().length() == 0 
            || CMCS9970.END_SEQ.charAt(0) == 0X00))) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_INPUT_INQ_SEQ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CMCS9970.REV_FLG != '1' 
            && CMCS9970.REV_FLG != '2') {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_INPUT_REV_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CMCS9970.BUS_TYP == null) CMCS9970.BUS_TYP = "";
        JIBS_tmp_int = CMCS9970.BUS_TYP.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) CMCS9970.BUS_TYP += " ";
        if (CMCS9970.TRF_TYP == 'T' 
            && (!CMCS9970.BUS_TYP.substring(0, 4).equalsIgnoreCase("CM22"))) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_TRF_TYP_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B120_GET_FITYP_SYS_INFO();
        if (pgmRtn) return;
        if (BPCFITYP.DATA_TXT.BSP_SERV_CODE.trim().length() > 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_FITYP_PARM_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCFITYP.DATA_TXT.GEN_PROC.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_FITYP_PARM_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B120_GET_FITYP_SYS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFITYP);
        IBS.init(SCCGWA, SCCPRMR);
        SCCPRMR.FUNC = ' ';
        BPCFITYP.KEY.TYP = "FITYP";
        BPCFITYP.KEY.CD = CMCS9970.BUS_TYP;
        SCCPRMR.DAT_PTR = BPCFITYP;
        IBS.CALLCPN(SCCGWA, "SC-PARM-READ", SCCPRMR);
        if (SCCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SCCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCFITYP.DATA_TXT.SCENE_FLG);
        CEP.TRC(SCCGWA, BPCFITYP.DATA_TXT.BSP_SERV_CODE);
        CEP.TRC(SCCGWA, BPCFITYP.DATA_TXT.BSP_REV_CODE);
        CEP.TRC(SCCGWA, BPCFITYP.DATA_TXT.GEN_PROC);
    }
    public void B200_GEN_PARM_DATA() throws IOException,SQLException,Exception {
        WS_PARM.WS_BUS_TYPE_INFO.WS_REQ_SYS_ID = CMCS9970.REQ_SYS;
        WS_PARM.WS_HOST_FNAME_INFO.WS_RTN_FILE.WS_BUS_TYPE1 = CMCS9970.BUS_TYP;
        WS_PARM.WS_BUS_TYPE_INFO.WS_BUS_TYPE2 = CMCS9970.BUS_TYP;
        WS_PARM.WS_BUS_TYPE_INFO.WS_SERV_CODE = BPCFITYP.DATA_TXT.BSP_SERV_CODE;
        WS_PARM.WS_HOST_FNAME_INFO.WS_RTN_FILE.WS_BAT_NO1 = SCCGWA.COMM_AREA.JRN_NO;
        WS_PARM.WS_BUS_TYPE_INFO.WS_BAT_NO2 = SCCGWA.COMM_AREA.JRN_NO;
        WS_PARM.WS_BUS_TYPE_INFO.WS_TRS_DATE = CMCS9970.BUS_DATE;
        WS_OUT_FIL_INF.WS_OUT_SYS_NM = CMCS9970.BUS_ID;
        CEP.TRC(SCCGWA, WS_OUT_FIL_INF.WS_OUT_SYS_NM);
        WS_OUT_FIL_INF.WS_OUT_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUT_FIL_INF.WS_OUT_FILE = IBS.CLS2CPY(SCCGWA, WS_PARM.WS_HOST_FNAME_INFO.WS_RTN_FILE);
        WS_PARM.WS_TRS_INFO.WS_FILE_SEQ1 = IBS.CLS2CPY(SCCGWA, WS_OUT_FIL_INF);
    }
    public void B300_GEN_CMTTRSTA_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCTLIBB);
        IBS.init(SCCGWA, CMRTRSTA);
        CMCTLIBB.FUNC = '0';
        CMCTLIBB.LEN = 173;
        CMCTLIBB.POINTER = CMRTRSTA;
        CMRTRSTA.KEY.FILE_NAME = IBS.CLS2CPY(SCCGWA, WS_PARM.WS_HOST_FNAME_INFO.WS_RTN_FILE);
        CEP.TRC(SCCGWA, CMRTRSTA.KEY.FILE_NAME);
        if (CMCS9970.BUS_DATE == 0) {
            CMRTRSTA.SYS_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            CMRTRSTA.SYS_DATE = CMCS9970.BUS_DATE;
        }
        CEP.TRC(SCCGWA, CMRTRSTA.SYS_DATE);
        CMRTRSTA.BU_TYPE = CMCS9970.BUS_TYP;
        CEP.TRC(SCCGWA, CMRTRSTA.BU_TYPE);
        CMRTRSTA.SCENE_FLG = 'B';
        CMRTRSTA.FILE_PROC_STS = "01";
        CMRTRSTA.MSG = "RECEIVE REQUEST";
        S000_CALL_CMZTRSTA();
        if (pgmRtn) return;
        if (CMCTLIBB.RETURN_INFO != 'F') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CMCTLIBB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B400_GEN_CMTREQTR_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRREQTR);
        if (CMRREQTR.KEY.REQ_SEQ == null) CMRREQTR.KEY.REQ_SEQ = "";
        JIBS_tmp_int = CMRREQTR.KEY.REQ_SEQ.length();
        for (int i=0;i<22-JIBS_tmp_int;i++) CMRREQTR.KEY.REQ_SEQ += " ";
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CMRREQTR.KEY.REQ_SEQ = JIBS_tmp_str[0] + CMRREQTR.KEY.REQ_SEQ.substring(8);
        if (CMRREQTR.KEY.REQ_SEQ == null) CMRREQTR.KEY.REQ_SEQ = "";
        JIBS_tmp_int = CMRREQTR.KEY.REQ_SEQ.length();
        for (int i=0;i<22-JIBS_tmp_int;i++) CMRREQTR.KEY.REQ_SEQ += " ";
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CMRREQTR.KEY.REQ_SEQ = CMRREQTR.KEY.REQ_SEQ.substring(0, 9 - 1) + JIBS_tmp_str[0] + CMRREQTR.KEY.REQ_SEQ.substring(9 + 12 - 1);
        CMRREQTR.SRC_SYS = CMCS9970.REQ_SYS;
        CMRREQTR.FILE_TYPE = CMCS9970.BUS_TYP;
        CMRREQTR.FILE_DATE = CMCS9970.BUS_DATE;
        CMRREQTR.FILE_SEQ = "" + 0;
        JIBS_tmp_int = CMRREQTR.FILE_SEQ.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) CMRREQTR.FILE_SEQ = "0" + CMRREQTR.FILE_SEQ;
        CMRREQTR.BLOB_INP_AREA = IBS.CLS2CPY(SCCGWA, CMCS9970);
        CMRREQTR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CMRREQTR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CMRREQTR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CMRREQTR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_CMTREQTR();
        if (pgmRtn) return;
        WS_PARM.WS_TRI_INFO.WS_FILE_SEQ2 = CMRREQTR.KEY.REQ_SEQ;
    }
    public void B500_INQ_DETAIL_PROC() throws IOException,SQLException,Exception {
        S000_CALL_CMZSBOA();
        if (pgmRtn) return;
    }
    public void B600_SUB_BSP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_PROC_INFO);
        WS_PROC_INFO.WS_SCENE_FLG = BPCFITYP.DATA_TXT.SCENE_FLG;
        WS_PROC_INFO.WS_AP_TYPE = CMCS9970.BUS_TYP;
        WS_PROC_INFO.WS_SYS_ID = CMCS9970.REQ_SYS;
        WS_PROC_INFO.WS_PROC_INF.WS_PROC_NAME[01-1] = BPCFITYP.DATA_TXT.GEN_PROC;
        WS_PROC_INFO.WS_PARM_DA1 = IBS.CLS2CPY(SCCGWA, WS_PARM.WS_HOST_FNAME_INFO);
        WS_PROC_INFO.WS_PARM_DA2 = IBS.CLS2CPY(SCCGWA, WS_PARM.WS_TRS_INFO);
        WS_PROC_INFO.WS_PARM_DA3 = IBS.CLS2CPY(SCCGWA, WS_PARM.WS_BUS_TYPE_INFO);
        WS_PROC_INFO.WS_PARM_DA4 = IBS.CLS2CPY(SCCGWA, WS_PARM.WS_TRI_INFO);
        if (WS_PROC_INFO.WS_AP_TYPE.trim().length() > 0) {
            JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.JRN_NO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(11 - 1, 11 + 2 - 1).trim().length() == 0) WS_PROC_INFO.WS_PROC_DD = 0;
            else WS_PROC_INFO.WS_PROC_DD = Short.parseShort(JIBS_tmp_str[0].substring(11 - 1, 11 + 2 - 1));
        } else {
            JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_PROC_INFO.WS_PROC_DD = 0;
            else WS_PROC_INFO.WS_PROC_DD = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        }
        R_SUB_PROCESS();
        if (pgmRtn) return;
    }
    public void B900_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCO9970);
        if (CMCO9970.FILE_NAM == null) CMCO9970.FILE_NAM = "";
        JIBS_tmp_int = CMCO9970.FILE_NAM.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) CMCO9970.FILE_NAM += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_PARM.WS_HOST_FNAME_INFO.WS_RTN_FILE);
        CMCO9970.FILE_NAM = JIBS_tmp_str[0] + CMCO9970.FILE_NAM.substring(19);
        if (CMCO9970.FILE_NAM == null) CMCO9970.FILE_NAM = "";
        JIBS_tmp_int = CMCO9970.FILE_NAM.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) CMCO9970.FILE_NAM += " ";
        CMCO9970.FILE_NAM = CMCO9970.FILE_NAM.substring(0, 20 - 1) + "/" + CMCO9970.FILE_NAM.substring(20 + 1 - 1);
        if (CMCO9970.FILE_NAM == null) CMCO9970.FILE_NAM = "";
        JIBS_tmp_int = CMCO9970.FILE_NAM.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) CMCO9970.FILE_NAM += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OUT_FIL_INF);
        CMCO9970.FILE_NAM = CMCO9970.FILE_NAM.substring(0, 21 - 1) + JIBS_tmp_str[0] + CMCO9970.FILE_NAM.substring(21 + 32 - 1);
        if (CMCO9970.FILE_NAM == null) CMCO9970.FILE_NAM = "";
        JIBS_tmp_int = CMCO9970.FILE_NAM.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) CMCO9970.FILE_NAM += " ";
        CMCO9970.FILE_NAM = CMCO9970.FILE_NAM.substring(0, 53 - 1) + ".txt" + CMCO9970.FILE_NAM.substring(53 + 4 - 1);
        if (CMCO9970.FILE_NAM == null) CMCO9970.FILE_NAM = "";
        JIBS_tmp_int = CMCO9970.FILE_NAM.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) CMCO9970.FILE_NAM += " ";
        CMCO9970.FILE_NAM = CMCO9970.FILE_NAM.substring(0, 21 - 1) + "CBS" + CMCO9970.FILE_NAM.substring(21 + 3 - 1);
        if (CMCO9970.FILE_NAM == null) CMCO9970.FILE_NAM = "";
        JIBS_tmp_int = CMCO9970.FILE_NAM.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) CMCO9970.FILE_NAM += " ";
        CMCO9970.FILE_NAM = CMCO9970.FILE_NAM.substring(0, 24 - 1) + "_" + CMCO9970.FILE_NAM.substring(24 + 1 - 1);
        if (CMCO9970.FILE_NAM == null) CMCO9970.FILE_NAM = "";
        JIBS_tmp_int = CMCO9970.FILE_NAM.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) CMCO9970.FILE_NAM += " ";
        CMCO9970.FILE_NAM = CMCO9970.FILE_NAM.substring(0, 33 - 1) + "_" + CMCO9970.FILE_NAM.substring(33 + 1 - 1);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CM997";
        SCCFMT.ODM_FLG = 'Y';
        SCCFMT.DATA_PTR = CMCO9970;
        SCCFMT.DATA_LEN = 60;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R_SUB_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORSYS);
        WS_PARM_LEN = 359;
        SORSYS.KEY.SYS_ID = " ";
        T000_READ_SOTSYS();
        if (pgmRtn) return;
        WS_PROC_INFO.WS_PROC_ENV = SORSYS.ENV_ID;
        CEP.TRC(SCCGWA, WS_PROC_INFO.WS_SYS_ID);
        CEP.TRC(SCCGWA, WS_PROC_INFO);
        WS_STAR_COMM = new SCZEXIT_WS_STAR_COMM();
        WS_STAR_COMM.STAR_PGM = "CMZSSUBP";
        WS_STAR_COMM.STAR_DATA = WS_PROC_INFO;
        WS_STAR_COMM.STAR_LEN = WS_PARM_LEN;
        IBS.START(SCCGWA, WS_STAR_COMM, true);
        CEP.TRC(SCCGWA, WS_PARM_LEN);
    }
    public void S000_CALL_CMZSBOA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-INQ-DZ", CMCS9970);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_CMZTRSTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-U-MAINTAIN-RSTA", CMCTLIBB);
    }
    public void T000_WRITE_CMTREQTR() throws IOException,SQLException,Exception {
        CMTREQTR_RD = new DBParm();
        CMTREQTR_RD.TableName = "CMTREQTR";
        CMTREQTR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, CMRREQTR, CMTREQTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_REQTR_REC_DUP;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_SOTSYS() throws IOException,SQLException,Exception {
        SOTSYS_RD = new DBParm();
        SOTSYS_RD.TableName = "SOTSYS";
        IBS.READ(SCCGWA, SORSYS, SOTSYS_RD);
    }
    public void T000_READ_UPD_SCTCWA() throws IOException,SQLException,Exception {
        SCRPCWA.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SCTCWA_RD = new DBParm();
        SCTCWA_RD.TableName = "SCTCWA";
        IBS.READ(SCCGWA, SCRPCWA, SCTCWA_RD);
