package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BAZUPYRT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String EVENT_CODE_RETURN = "MART";
    String EVENT_CODE_PAST = "MARTNX";
    String OUTPUT_FMT = "BAC03";
    String CMS = "CMS";
    char BLK_STS_BLOCK = '0';
    char BILL_STS_PAY = '4';
    char BILL_STS_WAIT_PAY = '3';
    char BILL_TYP_PAPER = 'P';
    char CLCT_STS_PYRT = '1';
    char MST1_STS_PYRT = '1';
    char DPAY_STS_PYRT = '5';
    char PAY_CHNL = '2';
    String HIS_REMARKS = "BANK BILL SOLUTION PAY RETURN";
    BAZUPYRT_WS_VARIABLES WS_VARIABLES = new BAZUPYRT_WS_VARIABLES();
    BAZUPYRT_WS_DATA_TO_SCZTPCL WS_DATA_TO_SCZTPCL = new BAZUPYRT_WS_DATA_TO_SCZTPCL();
    BACMSG_ERROR_MSG ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCTPCL SCCTPCL = new SCCTPCL();
    BACUPROD BACUPROD = new BACUPROD();
    BACFMST1 BACFMST1 = new BACFMST1();
    BARMST1 BARMST1 = new BARMST1();
    BACFTXDL BACFTXDL = new BACFTXDL();
    BARTXDL BARTXDL = new BARTXDL();
    BACFCLCT BACFCLCT = new BACFCLCT();
    BARCLCT BARCLCT = new BARCLCT();
    BACFDPAY BACFDPAY = new BACFDPAY();
    BARDPAY BARDPAY = new BARDPAY();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    AICPQIA AICPQIA = new AICPQIA();
    AICUUPIA AICUUPIA = new AICUUPIA();
    BPCSCGWY BPCSCGWY = new BPCSCGWY();
    BPCAOTH BPCAOTH = new BPCAOTH();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    CICMACR CICMACR = new CICMACR();
    SCCGWA SCCGWA;
    BACUPYRT BACUPYRT;
    public void MP(SCCGWA SCCGWA, BACUPYRT BACUPYRT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACUPYRT = BACUPYRT;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAZUPYRT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_DATA_TO_SCZTPCL);
        BACUPYRT.RC.RC_APP = "BA";
        BACUPYRT.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_GET_BILL_INFO();
        if (pgmRtn) return;
        B100_ADD_ACCOUTING();
        if (pgmRtn) return;
        B100_UPD_BILL_INFO();
        if (pgmRtn) return;
        if (BARMST1.PAY_CHNL != PAY_CHNL) {
            B100_ADD_CLCT();
            if (pgmRtn) return;
        }
        B100_ADD_DPAY();
        if (pgmRtn) return;
        B100_ADD_TXDL();
        if (pgmRtn) return;
        B200_RLTM_INFORM_CMS();
        if (pgmRtn) return;
        B300_REOPEN_CI_RELATION();
        if (pgmRtn) return;
    }
    public void B100_GET_BILL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFMST1);
        IBS.init(SCCGWA, BARMST1);
        BACFMST1.FUNC = 'T';
        BARMST1.BILL_NO = BACUPYRT.DATA.ID_NO;
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GET_BILL_INFO");
        if (BARMST1.BILL_TYP != BILL_TYP_PAPER) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BA_ERR_BILL_M_P;
            if (BARMST1.BILL_TYP == ' ') WS_VARIABLES.FLD_NO = 0;
            else WS_VARIABLES.FLD_NO = Short.parseShort(""+BARMST1.BILL_TYP);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BARMST1.BLK_STS == BLK_STS_BLOCK) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BA_BLK_STS_FROZ;
            if (BARMST1.BLK_STS == ' ') WS_VARIABLES.FLD_NO = 0;
            else WS_VARIABLES.FLD_NO = Short.parseShort(""+BARMST1.BLK_STS);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BARMST1.BILL_STS != BILL_STS_PAY) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BA_BILL_STS_M_PAY;
            if (BARMST1.BILL_STS == ' ') WS_VARIABLES.FLD_NO = 0;
            else WS_VARIABLES.FLD_NO = Short.parseShort(""+BARMST1.BILL_STS);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCSCGWY);
        BPCSCGWY.CHANG_WAY = "P025";
        BPCSCGWY.PRDT_CODE = BARMST1.PROD_CD;
        BPCSCGWY.FUNC = 'Q';
        CEP.TRC(SCCGWA, BPCSCGWY.CHANG_WAY);
        CEP.TRC(SCCGWA, BPCSCGWY.PRDT_CODE);
        S000_CALL_BPZSCGWY();
        if (pgmRtn) return;
        IBS.CPY2CLS(SCCGWA, BPCSCGWY.PARM_CODE, WS_VARIABLES.WS_PRM_KEY);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_PRM_KEY);
        IBS.init(SCCGWA, BACUPROD);
        BACUPROD.FUNC = 'I';
        BACUPROD.KEY.TYPE = "PRDPR";
        BACUPROD.KEY.CODE = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_PRM_KEY);
        S000_CALL_BAZUPROD();
        if (pgmRtn) return;
        if (BARMST1.PAY_CHNL == '2') {
            IBS.init(SCCGWA, AICPQIA);
            AICPQIA.CD.AC_TYP = "2";
            AICPQIA.CD.BUSI_KND = "XXXXX";
            AICPQIA.BR = BARMST1.ACPT_BR;
            AICPQIA.CCY = BARMST1.BILL_CUR;
            AICPQIA.SIGN = 'C';
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = AICPQIA.AC;
            AICUUPIA.DATA.CCY = BARMST1.BILL_CUR;
            AICUUPIA.DATA.AMT = BARMST1.BILL_AMT;
            AICUUPIA.DATA.VALUE_DATE = BARMST1.PAY_DT;
            AICUUPIA.DATA.EVENT_CODE = "CR";
            IBS.init(SCCGWA, AICPQIA);
            AICPQIA.CD.AC_TYP = "2";
            AICPQIA.CD.BUSI_KND = "XXXXX";
            AICPQIA.BR = BARMST1.ACPT_BR;
            AICPQIA.CCY = BARMST1.BILL_CUR;
            AICPQIA.SIGN = 'D';
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = AICPQIA.AC;
            AICUUPIA.DATA.CCY = BARMST1.BILL_CUR;
            AICUUPIA.DATA.AMT = BARMST1.BILL_AMT;
            AICUUPIA.DATA.VALUE_DATE = BARMST1.PAY_DT;
            AICUUPIA.DATA.EVENT_CODE = "DR";
        }
    }
    public void B100_ADD_ACCOUTING() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        BPCPEBAS.DATA.CNTR_INFO.CNTR_TYPE_ORG[1-1] = BACUPROD.DATA_TXT.PRODMO;
        S000_CALL_BPZPEBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = BACUPROD.DATA_TXT.PRODMO;
        BPCPOEWA.DATA.PROD_CODE = BARMST1.PROD_CD;
        BPCPOEWA.DATA.BR_OLD = BARMST1.ACPT_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BARMST1.BILL_CUR;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AC_NO = BARMST1.CTA_NO;
        if (BARMST1.GL_TRSF_FLG == 'Y') {
            BPCPOEWA.DATA.AMT_INFO[2-1].AMT = BARMST1.BILL_AMT;
            if (BARMST1.BILL_TYP == 'P' 
                && BARMST1.WO_PAY_FLG != 'Y' 
                && BARMST1.PAY_CHNL != '2') {
                BPCPOEWA.DATA.AMT_INFO[3-1].AMT = BARMST1.BILL_AMT;
            }
            BPCPOEWA.DATA.EVENT_CODE = EVENT_CODE_RETURN;
        } else {
            if (BARMST1.PAY_DT == SCCGWA.COMM_AREA.AC_DATE) {
                BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BARMST1.BILL_AMT;
                CEP.TRC(SCCGWA, "DDDDDDDD");
                CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
                if (BARMST1.BILL_TYP == 'P' 
                    && BARMST1.WO_PAY_FLG != 'Y' 
                    && BARMST1.PAY_CHNL != '2') {
                    BPCPOEWA.DATA.AMT_INFO[3-1].AMT = BARMST1.BILL_AMT;
                }
                BPCPOEWA.DATA.EVENT_CODE = EVENT_CODE_RETURN;
            } else {
                BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BARMST1.BILL_AMT;
                BPCPOEWA.DATA.AMT_INFO[2-1].AMT = BARMST1.BILL_AMT;
                if (BARMST1.BILL_TYP == 'P' 
                    && BARMST1.WO_PAY_FLG != 'Y' 
                    && BARMST1.PAY_CHNL != '2') {
                    BPCPOEWA.DATA.AMT_INFO[3-1].AMT = BARMST1.BILL_AMT;
                }
                BPCPOEWA.DATA.EVENT_CODE = EVENT_CODE_PAST;
            }
        }
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B100_UPD_BILL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFMST1);
        BACFMST1.FUNC = 'U';
        if (BARMST1.PAY_CHNL == PAY_CHNL) {
            BARMST1.BILL_STS = MST1_STS_PYRT;
        } else {
            BARMST1.BILL_STS = BILL_STS_WAIT_PAY;
        }
        if (BARMST1.GL_TRSF_FLG != 'Y' 
            && BARMST1.PAY_DT != SCCGWA.COMM_AREA.AC_DATE) {
            BARMST1.GL_TRSF_FLG = 'Y';
        }
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
    }
    public void B100_ADD_CLCT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFCLCT);
        IBS.init(SCCGWA, BARCLCT);
        WS_VARIABLES.SEQ = 0;
        BACFCLCT.FUNC = 'L';
        BARCLCT.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARCLCT.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        S000_CALL_BAZFCLCT_1();
        if (pgmRtn) return;
        WS_VARIABLES.SEQ = BARCLCT.SEQ;
        WS_VARIABLES.SEQ += 1;
        CEP.TRC(SCCGWA, BARCLCT.CNTR_NO);
        CEP.TRC(SCCGWA, BARCLCT.ACCT_CNT);
        CEP.TRC(SCCGWA, BARCLCT.BILL_NO);
        CEP.TRC(SCCGWA, BARCLCT.SEQ);
        CEP.TRC(SCCGWA, WS_VARIABLES.SEQ);
        IBS.init(SCCGWA, BACFCLCT);
        BACFCLCT.FUNC = 'A';
        BARCLCT.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARCLCT.KEY.CRE_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BARCLCT.ACPT_BR = BARMST1.ACPT_BR;
        BARCLCT.BILL_NO = BACUPYRT.DATA.ID_NO;
        BARCLCT.SEQ = WS_VARIABLES.SEQ;
        BARCLCT.CLCT_STS = CLCT_STS_PYRT;
        BARCLCT.BILL_STS = BARMST1.BILL_STS;
        BARCLCT.PAY_CHNL = BARMST1.PAY_CHNL;
        BARCLCT.WO_PAY_FLG = BARMST1.WO_PAY_FLG;
        if (BARMST1.WO_PAY_FLG == 'Y') {
            BARCLCT.REMK = "3";
        } else {
            BARCLCT.REMK = "1";
        }
        S000_CALL_BAZFCLCT();
        if (pgmRtn) return;
    }
    public void B100_ADD_DPAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFDPAY);
        IBS.init(SCCGWA, BARDPAY);
        WS_VARIABLES.SEQ = 0;
        BACFDPAY.FUNC = 'L';
        BARDPAY.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARDPAY.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        S000_CALL_BAZFDPAY();
        if (pgmRtn) return;
        WS_VARIABLES.SEQ = BARDPAY.SEQ;
        WS_VARIABLES.SEQ += 1;
        CEP.TRC(SCCGWA, BARDPAY.SEQ);
        CEP.TRC(SCCGWA, WS_VARIABLES.SEQ);
        IBS.init(SCCGWA, BACFDPAY);
        BACFDPAY.FUNC = 'A';
        BARDPAY.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARDPAY.KEY.CRE_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BARDPAY.BILL_NO = BACUPYRT.DATA.ID_NO;
        BARDPAY.ACPT_BR = BARMST1.ACPT_BR;
        BARDPAY.AMT_STS = BARMST1.AMT_STS;
        BARDPAY.PLDG_STS = BARMST1.PLDG_STS;
        BARDPAY.BLK_STS = BARMST1.BLK_STS;
        BARDPAY.WO_PAY_FLG = BARMST1.WO_PAY_FLG;
        BARDPAY.CHG_DRW_FLG = BARMST1.CHG_DRW_FLG;
        BARDPAY.ORG_BIL_NO = BARMST1.ORG_BIL_NO;
        if (BARMST1.WO_PAY_FLG == 'Y') {
            BARDPAY.REMK = "3";
        } else {
            BARDPAY.REMK = "1";
        }
        BARDPAY.SEQ = WS_VARIABLES.SEQ;
        BARDPAY.DPAY_STS = DPAY_STS_PYRT;
        if (BARMST1.PAY_CHNL == PAY_CHNL) {
            BARDPAY.BILL_STS = MST1_STS_PYRT;
        } else {
            BARDPAY.BILL_STS = BILL_STS_WAIT_PAY;
        }
        S000_CALL_BAZFDPAY();
        if (pgmRtn) return;
    }
    public void B100_ADD_TXDL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFTXDL);
        IBS.init(SCCGWA, BARTXDL);
        BACFTXDL.FUNC = 'A';
        BARTXDL.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.KEY.CRE_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BARTXDL.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BARTXDL.PRPH_SYS_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BARTXDL.PRPH_SYS_DT = SCCGWA.COMM_AREA.TR_DATE;
        BARTXDL.PRPH_JRN = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = BARTXDL.PRPH_JRN.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BARTXDL.PRPH_JRN = "0" + BARTXDL.PRPH_JRN;
        BARTXDL.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BARTXDL.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BARTXDL.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BARTXDL.UPDT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.UPDT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BARTXDL.BILL_NO = BACUPYRT.DATA.ID_NO;
        BARTXDL.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARTXDL.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        BARTXDL.TX_AC = BARMST1.DRWR_AC;
        BARTXDL.SUSP_NO = BARMST1.SUSP_NO;
        BARTXDL.TX_CUR = BARMST1.BILL_CUR;
        BARTXDL.TX_AMT = BARMST1.BILL_AMT;
        BARTXDL.REC_FLG = '1';
        S000_CALL_BAZFTXDL();
        if (pgmRtn) return;
    }
    public void B200_RLTM_INFORM_CMS() throws IOException,SQLException,Exception {
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
            WS_DATA_TO_SCZTPCL.OUT_AMT = BARMST1.BILL_AMT;
            WS_DATA_TO_SCZTPCL.OUT_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
            WS_DATA_TO_SCZTPCL.OUT_REV_FLG = SCCGWA.COMM_AREA.CANCEL_IND;
            WS_DATA_TO_SCZTPCL.TR_TYP = '1';
            SCCTPCL.INP_AREA.SERV_DATA_LEN = 93;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, WS_DATA_TO_SCZTPCL);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_SMS_INFO);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_SG0001);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0002);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0001);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0001);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SN_SMS_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.CC_CHANGE_CARD);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SC_SCF_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_IBIL_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CASH_CHG);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0003);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BROADCAST);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CSIF_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BVMS_INQ);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0002);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PASSWORD_CHK);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_SEND);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_ECIF);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_AC_CHG_INF);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CHG_NOTICE);
            S000_CALL_SCZTPCL();
            if (pgmRtn) return;
        }
    }
    public void B300_REOPEN_CI_RELATION() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMACR);
        CICMACR.FUNC = '5';
        CICMACR.DATA.ENTY_TYP = '1';
        CICMACR.DATA.AGR_NO = BARMST1.CTA_NO;
        S000_CALL_CIZMACR();
        if (pgmRtn) return;
    }
    public void S000_CALL_BAZFMST1() throws IOException,SQLException,Exception {
        BACFMST1.REC_PTR = BARMST1;
        BACFMST1.REC_LEN = 1163;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFMST1", BACFMST1);
        if (BACFMST1.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BA_MST1_NOTFND, BACUPYRT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZUPROD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-PROD-MAINTAIN", BACUPROD);
        if (BACUPROD.RC.RC_RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUPROD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSCGWY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCGWY.PRDT_CODE);
        CEP.TRC(SCCGWA, BPCSCGWY.CHANG_WAY);
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-CGWY", BPCSCGWY);
        if (BPCSCGWY.RC.RC_CODE != 0) {
            BACUPYRT.RC.RC_APP = BPCSCGWY.RC.RC_MMO;
            BACUPYRT.RC.RC_RTNCODE = BPCSCGWY.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
        if (AICPQIA.RC.RC_CODE != 0 
            || JIBS_tmp_str[1].trim().length() == 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUPYRT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        CEP.TRC(SCCGWA, AICUUPIA.RC);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUPYRT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZFCLCT() throws IOException,SQLException,Exception {
        BACFCLCT.REC_PTR = BARCLCT;
        BACFCLCT.REC_LEN = 1021;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFCLCT", BACFCLCT);
        if (BACFCLCT.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFCLCT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZFCLCT_1() throws IOException,SQLException,Exception {
        BACFCLCT.REC_PTR = BARCLCT;
        BACFCLCT.REC_LEN = 1021;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFCLCT", BACFCLCT);
    }
    public void S000_CALL_BAZFDPAY() throws IOException,SQLException,Exception {
        BACFDPAY.REC_PTR = BARDPAY;
        BACFDPAY.REC_LEN = 379;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFDPAY", BACFDPAY);
        if (BACFDPAY.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFDPAY.RC);
            S000_ERR_MSG_PROC();
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
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUPYRT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUCNGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-CNGM", BPCUCNGM);
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCNGM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUPYRT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void S000_CALL_CIZMACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ACR", CICMACR);
        if (CICMACR.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMACR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
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
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
