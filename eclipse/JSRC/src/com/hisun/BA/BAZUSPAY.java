package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import com.hisun.DD.*;
import com.hisun.GD.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAZUSPAY {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CMS = "CMS";
    BAZUSPAY_WS_VARIABLES WS_VARIABLES = new BAZUSPAY_WS_VARIABLES();
    BAZUSPAY_WS_BILL_TEMP WS_BILL_TEMP = new BAZUSPAY_WS_BILL_TEMP();
    BAZUSPAY_WS_DATA_TO_SCZTPCL WS_DATA_TO_SCZTPCL = new BAZUSPAY_WS_DATA_TO_SCZTPCL();
    BACMSG_ERROR_MSG ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCTPCL SCCTPCL = new SCCTPCL();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    BACFMST1 BACFMST1 = new BACFMST1();
    BARMST1 BARMST1 = new BARMST1();
    BARTXDL BARTXDL = new BARTXDL();
    BACFTXDL BACFTXDL = new BACFTXDL();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    AICPQIA AICPQIA = new AICPQIA();
    GDCIQLDR GDCIQLDR = new GDCIQLDR();
    GDCUMPLD GDCUMPLD = new GDCUMPLD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGAPV SCCGAPV;
    BACUSPAY BACUSPAY;
    public void MP(SCCGWA SCCGWA, BACUSPAY BACUSPAY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACUSPAY = BACUSPAY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAZUSPAY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_DATA_TO_SCZTPCL);
        BACUSPAY.RC.RC_MMO = "BA";
        BACUSPAY.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_PROC_MAIN();
        if (pgmRtn) return;
        B030_RLTM_INFORM_CMS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_PROC_MAIN() throws IOException,SQLException,Exception {
        B021_READ_BILL_INFO();
        if (pgmRtn) return;
        B022_CHECK_AUTH_AND_STS();
        if (pgmRtn) return;
        B023_DEDUCT_AMT_PROC();
        if (pgmRtn) return;
        B024_SET_EWA_EVENT();
        if (pgmRtn) return;
        B026_UPDATE_MST1();
        if (pgmRtn) return;
        B027_ADD_TXDL();
        if (pgmRtn) return;
    }
    public void B021_READ_BILL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFMST1);
        IBS.init(SCCGWA, BARMST1);
        IBS.init(SCCGWA, WS_BILL_TEMP);
        BACFMST1.FUNC = 'T';
        BARMST1.BILL_NO = BACUSPAY.BILL_NO;
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
        WS_BILL_TEMP.CNTR_NO = BARMST1.KEY.CNTR_NO;
        WS_BILL_TEMP.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        WS_BILL_TEMP.ACPT_BR = BARMST1.ACPT_BR;
        WS_BILL_TEMP.AMT_STS = BARMST1.AMT_STS;
        WS_BILL_TEMP.BILL_STS = BARMST1.BILL_STS;
        WS_BILL_TEMP.PLDG_STS = BARMST1.PLDG_STS;
        WS_BILL_TEMP.BLK_STS = BARMST1.BLK_STS;
        WS_BILL_TEMP.WO_PAY_FLG = BARMST1.WO_PAY_FLG;
        WS_BILL_TEMP.CHG_DRW_FLG = BARMST1.CHG_DRW_FLG;
        WS_BILL_TEMP.ORG_BIL_NO = BARMST1.ORG_BIL_NO;
        WS_BILL_TEMP.GL_TRSF_FLG = BARMST1.GL_TRSF_FLG;
        WS_BILL_TEMP.BILL_AMT = BARMST1.BILL_AMT;
        WS_BILL_TEMP.DRWR_AC = BARMST1.DRWR_AC;
        WS_BILL_TEMP.DBT_SEQ = BARMST1.DBT_SEQ;
        WS_BILL_TEMP.SUSP_NO = BARMST1.SUSP_NO;
        WS_BILL_TEMP.REM_AMT = BARMST1.REM_AMT;
    }
    public void B022_CHECK_AUTH_AND_STS() throws IOException,SQLException,Exception {
        if (BARMST1.BLK_STS == '0') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BA_BLK_STS_FROZ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_BILL_TEMP.AMT_STS);
        CEP.TRC(SCCGWA, WS_BILL_TEMP.REM_AMT);
        CEP.TRC(SCCGWA, WS_BILL_TEMP.BILL_STS);
        if (WS_BILL_TEMP.AMT_STS == '3' 
            && WS_BILL_TEMP.REM_AMT > 0 
            && WS_BILL_TEMP.BILL_STS != '5') {
            if (BACUSPAY.TRX_AMT > WS_BILL_TEMP.REM_AMT) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BA_ERR_TRX_AMT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BA_ERR_NON_REM_AMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B023_DEDUCT_AMT_PROC() throws IOException,SQLException,Exception {
        if (BACUSPAY.ACN_TYP == '1' 
            || BACUSPAY.ACN_TYP == '3') {
            if (BACUSPAY.ACN_TYP == '1') {
                if (!BACUSPAY.DBT_AC.equalsIgnoreCase(BARMST1.DRWR_AC)) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BA_ERR_AC_NOT_MTCH;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            B030_DEDUC_FROM_ACCOUNT();
            if (pgmRtn) return;
        } else {
            if (BACUSPAY.ACN_TYP == '2') {
                B033_AI_DEDUCT_PROC();
                if (pgmRtn) return;
            }
        }
        if (WS_BILL_TEMP.REM_AMT1 == 0) {
            B200_INQUIRE_DEPOSIT_INF();
            if (pgmRtn) return;
            if (GDCIQLDR.TCNT > 0) {
                for (WS_VARIABLES.GUAR_NUM = 1; WS_VARIABLES.GUAR_NUM <= GDCIQLDR.TCNT; WS_VARIABLES.GUAR_NUM += 1) {
                    if (GDCIQLDR.INFO[WS_VARIABLES.GUAR_NUM-1].RAMT > 0) {
                        B700_RELEASE_DEPOSIT_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void B200_INQUIRE_DEPOSIT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCIQLDR);
        if (BARMST1.B_REF_NO.trim().length() > 0) {
            GDCIQLDR.RSEQ = BARMST1.B_REF_NO;
            CEP.TRC(SCCGWA, GDCIQLDR.RSEQ);
            S000_CALL_GDZIQLDR();
            if (pgmRtn) return;
        }
    }
    public void B700_RELEASE_DEPOSIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCUMPLD);
        GDCUMPLD.FUNC = 'R';
        GDCUMPLD.RSEQ = GDCIQLDR.RSEQ;
        CEP.TRC(SCCGWA, WS_VARIABLES.GUAR_NUM);
        GDCUMPLD.JRN_SEQ = WS_VARIABLES.GUAR_NUM;
        GDCUMPLD.AC = GDCIQLDR.INFO[WS_VARIABLES.GUAR_NUM-1].OUT_AC;
        GDCUMPLD.AC_TYPE = GDCIQLDR.INFO[WS_VARIABLES.GUAR_NUM-1].AC_TYP;
        GDCUMPLD.CCY = GDCIQLDR.INFO[WS_VARIABLES.GUAR_NUM-1].CCY;
        GDCUMPLD.TX_AMT = GDCIQLDR.INFO[WS_VARIABLES.GUAR_NUM-1].RAMT;
        GDCUMPLD.CCY_TYPE = '1';
        CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.GUAR_NUM-1].OUT_AC);
        CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.GUAR_NUM-1].AC_TYP);
        CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.GUAR_NUM-1].CCY);
        CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.GUAR_NUM-1].RAMT);
        CEP.TRC(SCCGWA, GDCUMPLD.AC);
        CEP.TRC(SCCGWA, GDCUMPLD.AC_TYPE);
        CEP.TRC(SCCGWA, GDCUMPLD.CCY);
        CEP.TRC(SCCGWA, GDCUMPLD.TX_AMT);
        S000_CALL_GDZUMPLD();
        if (pgmRtn) return;
    }
    public void S000_CALL_GDZUMPLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-RL", GDCUMPLD);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_DEDUC_FROM_ACCOUNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        if (BACUSPAY.ACN_TYP == '1') {
            DDCUDRAC.AC = BARMST1.DRWR_AC;
        } else {
            DDCUDRAC.AC = BACUSPAY.DBT_AC;
        }
        DDCUDRAC.CCY_TYPE = '1';
        DDCUDRAC.CCY = BARMST1.BILL_CUR;
        DDCUDRAC.TX_AMT = BACUSPAY.TRX_AMT;
        DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUDRAC.TX_MMO = "034";
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.TX_BAL_FLG = 'Y';
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
        WS_BILL_TEMP.F_TRXAMT = DDCUDRAC.TX_AMT;
        BACUSPAY.TRX_AMT = WS_BILL_TEMP.F_TRXAMT;
        WS_BILL_TEMP.REM_AMT1 = BARMST1.REM_AMT - WS_BILL_TEMP.F_TRXAMT;
    }
    public void B033_AI_DEDUCT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = BACUSPAY.DBT_AC;
        AICUUPIA.DATA.RVS_NO = BACUSPAY.SUSP_NO;
        AICUUPIA.DATA.CCY = BARMST1.BILL_CUR;
        AICUUPIA.DATA.AMT = BACUSPAY.TRX_AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
        WS_BILL_TEMP.F_TRXAMT = BACUSPAY.TRX_AMT;
        WS_BILL_TEMP.REM_AMT1 = BARMST1.REM_AMT - BACUSPAY.TRX_AMT;
    }
    public void S000_CALL_GDZIQLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-I-QLDR-PROC", GDCIQLDR);
        CEP.TRC(SCCGWA, GDCIQLDR.RC);
        if (GDCIQLDR.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCIQLDR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B024_SET_EWA_EVENT() throws IOException,SQLException,Exception {
        B024_10_SET_EWA_BASIC_INF();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CLBA";
        BPCPOEWA.DATA.PROD_CODE = BARMST1.PROD_CD;
        BPCPOEWA.DATA.AC_NO = BARMST1.CTA_NO;
        BPCPOEWA.DATA.EVENT_CODE = "BADROL";
        BPCPOEWA.DATA.BR_OLD = BARMST1.ACPT_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BARMST1.BILL_CUR;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.VALUE_DATE);
        BPCPOEWA.DATA.AMT_INFO[14-1].AMT = BACUSPAY.TRX_AMT;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B024_10_SET_EWA_BASIC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        BPCPEBAS.DATA.CNTR_INFO.CNTR_TYPE_ORG[1-1] = "CLBA";
        S000_CALL_BPZPEBAS();
        if (pgmRtn) return;
    }
    public void B026_UPDATE_MST1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFMST1);
        BACFMST1.FUNC = 'U';
        if (BACUSPAY.TRX_AMT == BARMST1.REM_AMT) {
            BARMST1.AMT_STS = '5';
        }
        BARMST1.REM_AMT = WS_BILL_TEMP.REM_AMT1;
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
    }
    public void B027_ADD_TXDL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARTXDL);
        IBS.init(SCCGWA, BACFTXDL);
        BACFTXDL.FUNC = 'A';
        BARTXDL.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.KEY.CRE_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BARTXDL.PRPH_SYS_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BARTXDL.PRPH_SYS_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.PRPH_JRN = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = BARTXDL.PRPH_JRN.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BARTXDL.PRPH_JRN = "0" + BARTXDL.PRPH_JRN;
        BARTXDL.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARTXDL.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        BARTXDL.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BARTXDL.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BARTXDL.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BARTXDL.BILL_NO = BACUSPAY.BILL_NO;
        BARTXDL.TX_AC = BACUSPAY.DBT_AC;
        BARTXDL.SUSP_NO = BACUSPAY.SUSP_NO;
        BARTXDL.TX_CUR = BARMST1.BILL_CUR;
        if (BACUSPAY.ACN_TYP == '2') {
            BARTXDL.TX_AMT = AICUUPIA.DATA.AMT;
        } else {
            BARTXDL.TX_AMT = WS_BILL_TEMP.F_TRXAMT;
        }
        BARTXDL.REC_FLG = '1';
        BARTXDL.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BARTXDL.UPDT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.UPDT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BARTXDL.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = BARTXDL.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BARTXDL.TS = "0" + BARTXDL.TS;
        S000_CALL_BAZFTXDL();
        if (pgmRtn) return;
    }
    public void B030_RLTM_INFORM_CMS() throws IOException,SQLException,Exception {
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            IBS.init(SCCGWA, SCCTPCL);
            if (BARMST1.BK_X50 == null) BARMST1.BK_X50 = "";
            JIBS_tmp_int = BARMST1.BK_X50.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) BARMST1.BK_X50 += " ";
            SCCTPCL.SERV_AREA.OBJ_SYSTEM = BARMST1.BK_X50.substring(0, 4);
            SCCTPCL.SERV_AREA.SERV_CODE = "132010";
            SCCTPCL.SERV_AREA.SERV_TYPE = ' ';
            WS_DATA_TO_SCZTPCL.OUT_CTA_NO = BARMST1.CTA_NO;
            WS_DATA_TO_SCZTPCL.OUT_PAPER_NO = BARMST1.KEY.CNTR_NO;
            WS_DATA_TO_SCZTPCL.OUT_DRAW_NO = BARMST1.KEY.ACCT_CNT;
            WS_DATA_TO_SCZTPCL.OUT_CCY = BARMST1.BILL_CUR;
            WS_DATA_TO_SCZTPCL.OUT_OSBAL = 0;
            WS_DATA_TO_SCZTPCL.OUT_AMT = WS_BILL_TEMP.F_TRXAMT;
            WS_DATA_TO_SCZTPCL.OUT_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
            WS_DATA_TO_SCZTPCL.OUT_REV_FLG = SCCGWA.COMM_AREA.CANCEL_IND;
            WS_DATA_TO_SCZTPCL.TR_TYP = '2';
            SCCTPCL.INP_AREA.SERV_DATA_LEN = 93;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, WS_DATA_TO_SCZTPCL);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0001);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0001);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0002);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_IBIL_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_SMS_INFO);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_SG0001);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0003);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BROADCAST);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BVMS_INQ);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_SEND);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_ECIF);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_AC_CHG_INF);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CHG_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SN_SMS_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.CC_CHANGE_CARD);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SC_SCF_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CASH_CHG);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CSIF_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0002);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PASSWORD_CHK);
            S000_CALL_SCZTPCL();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZFTXDL() throws IOException,SQLException,Exception {
        BACFTXDL.REC_PTR = BARTXDL;
        BACFTXDL.REC_LEN = 514;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFTXDL", BACFTXDL);
        if (BACFTXDL.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFTXDL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZFMST1() throws IOException,SQLException,Exception {
        BACFMST1.REC_PTR = BARMST1;
        BACFMST1.REC_LEN = 1163;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFMST1", BACFMST1);
        if (BACFMST1.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFMST1.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        CEP.TRC(SCCGWA, AICPQIA.AC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
        if (AICPQIA.RC.RC_CODE != 0 
            || JIBS_tmp_str[1].trim().length() == 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUSPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        CEP.TRC(SCCGWA, AICUUPIA.RC);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUSPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUSPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUSPAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC, true);
    }
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_VARIABLES.ERR_MSG, WS_VARIABLES.FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
