package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class IBZDRAC {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm IBTMST_RD;
    DBParm IBTSCASH_RD;
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    String TBL_IBTMST = "IBTMST  ";
    String K_DR_EVENT = "DR";
    String K_IB_MMO = "IB";
    String K_PROC_START = "SCPWAT93";
    String K_PROC_END = "IBPEOD22";
    String K_IB_PROD_MODEL = "IBDD";
    String K_OUTPUT_FMT = "IBT01";
    int WS_1_DATE = 0;
    String WS_PQORG_VIL_TYP = " ";
    String WS_PQORG_TRA_TYP = " ";
    char WS_DATA_OVERFLOW_FLAG = ' ';
    char WS_REC_FLG = ' ';
    char WS_SCASH_FLG = ' ';
    char WS_PROC_START_FLG = ' ';
    char WS_PROC_END_FLG = ' ';
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCCBSTS SCCCBSTS = new SCCCBSTS();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCBWEVT BPCBWEVT = new BPCBWEVT();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AICOCKOP AICOCKOP = new AICOCKOP();
    IBCQINF IBCQINF = new IBCQINF();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    IBRPHIS IBRPHIS = new IBRPHIS();
    IBCUBAL IBCUBAL = new IBCUBAL();
    IBCBALCK IBCBALCK = new IBCBALCK();
    BPCQCCY BPCQCCY = new BPCQCCY();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBRMST IBRMST = new IBRMST();
    IBRSCASH IBRSCASH = new IBRSCASH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    BPRVWA BPRVWA;
    SCCBATH SCCBATH;
    IBCPOSTA IBCPOSTA;
    public void MP(SCCGWA SCCGWA, IBCPOSTA IBCPOSTA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCPOSTA = IBCPOSTA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZDRAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPRVWA = (BPRVWA) SCCGBPA_BP_AREA.VCH_AREA.VCH_AREA_PTR;
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        IBS.init(SCCGWA, SCCMSG);
        IBS.CPY2CLS(SCCGWA, 0+"", IBCPOSTA.RC);
        IBCPOSTA.RC.RC_MMO = " ";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCPOSTA.VAL_DATE);
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_READ_MST();
        if (pgmRtn) return;
        B030_CHECK_STATUS();
        if (pgmRtn) return;
        if (IBCPOSTA.ENTRY_FLG != 'K') {
            if (IBCPOSTA.ENTRY_FLG == '1') {
                B059_GET_PROD_CODE();
                if (pgmRtn) return;
                B060_PROC_VOUCHER();
                if (pgmRtn) return;
                B070_WRITE_HIST();
                if (pgmRtn) return;
            }
            B080_UPD_BAL();
            if (pgmRtn) return;
            B090_UPD_MST();
            if (pgmRtn) return;
        }
        B100_WR_IBTBALF();
        if (pgmRtn) return;
    }
    public void B008_GETPROC_FLG() throws IOException,SQLException,Exception {
        WS_PROC_END_FLG = 'N';
        IBS.init(SCCGWA, SCCCBSTS);
        SCCCBSTS.PROC_NAME = K_PROC_END;
        S000_CALL_SCZCBSTS();
        if (pgmRtn) return;
        if (SCCCBSTS.PROC_STUS == 'K' 
            || SCCCBSTS.PROC_STUS == 'F') {
            WS_PROC_END_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_PROC_START_FLG);
        CEP.TRC(SCCGWA, WS_PROC_END_FLG);
        if (WS_PROC_END_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.SYS_BATH_ERR, IBCPOSTA.RC);
            CEP.TRC(SCCGWA, IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B059_GET_PROD_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = IBRMST.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        if (!BPCPQPRD.PRDT_MODEL.equalsIgnoreCase(K_IB_PROD_MODEL)) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOT_IB_PROD_TYP, IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCPOSTA.AC);
        if (IBCPOSTA.AC.trim().length() == 0 
            && (IBCPOSTA.NOSTRO_CD.trim().length() == 0 
            && IBCPOSTA.CCY.trim().length() == 0)) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INPUT, IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, IBCPOSTA.SIGN);
        if (IBCPOSTA.SIGN != 'D') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INPUT, IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
            && IBCPOSTA.AMT == 0) {
            B020_01_READ_IBTMST();
            if (pgmRtn) return;
            B080_02_01_UPD_IBTSCASH();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        B010_1_CHK_CCY();
        if (pgmRtn) return;
        if (IBCPOSTA.VAL_DATE != 0) {
            B010_2_CHK_VAL_DATE();
            if (pgmRtn) return;
        } else {
            IBCPOSTA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (IBCPOSTA.TX_MMO.trim().length() == 0) {
            IBCPOSTA.TX_MMO = "A001";
        }
        CEP.TRC(SCCGWA, IBCPOSTA.TX_MMO);
        if (IBCPOSTA.ENTRY_FLG != '1') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.ENTRY_FLG, IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_1_CHK_CCY() throws IOException,SQLException,Exception {
        if (IBCPOSTA.CCY.equalsIgnoreCase(" ")) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CCY, IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            S000_LINK_SCSOCCY();
            if (pgmRtn) return;
        }
    }
    public void B010_2_CHK_VAL_DATE() throws IOException,SQLException,Exception {
        if (IBCPOSTA.VAL_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (!(BPRVWA.BASIC_AREA.ODE_FLG == 'Y') 
                && !(BPRVWA.BASIC_AREA.INTF_MODE == 'O' 
                || BPRVWA.BASIC_AREA.INTF_MODE == 'B') 
                && !(SCCGWA.COMM_AREA.AP_MMO.equalsIgnoreCase("IB")) 
                && !(JIBS_tmp_str[0].equalsIgnoreCase("0016540"))) {
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                if (!JIBS_tmp_str[0].equalsIgnoreCase("162000")) {
                    WS_1_DATE = IBCPOSTA.VAL_DATE;
                    B010_2_3_CHK_VAL_DATE();
                    if (pgmRtn) return;
                }
            }
        }
        if (IBCPOSTA.VAL_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_NOT_SUPP_FORWORD, IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_2_3_CHK_VAL_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICOCKOP);
        AICOCKOP.VAL_DATE = WS_1_DATE;
        S00_CALL_AIZCKOP();
        if (pgmRtn) return;
    }
    public void B020_READ_MST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMST);
        CEP.TRC(SCCGWA, IBCPOSTA.NOSTRO_CD);
        CEP.TRC(SCCGWA, IBCPOSTA.CCY);
        if (IBCPOSTA.NOSTRO_CD.trim().length() > 0 
            && IBCPOSTA.AC.trim().length() == 0) {
            IBRMST.NOSTRO_CODE = IBCPOSTA.NOSTRO_CD;
            IBRMST.CCY = IBCPOSTA.CCY;
            T000_STARTBR_IBTMST_2();
            if (pgmRtn) return;
            IBCPOSTA.AC = IBRMST.KEY.AC_NO;
        }
        IBRMST.KEY.AC_NO = IBCPOSTA.AC;
        CEP.TRC(SCCGWA, IBCPOSTA.AC);
        T000_READ_UPD_IBTMST();
        if (pgmRtn) return;
        IBCPOSTA.NOSTRO_CD = IBRMST.NOSTRO_CODE;
        if (WS_REC_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST, IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBRMST.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.EFFECTIVE_DATE, IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBRMST.EFF_DATE > IBCPOSTA.VAL_DATE) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.EFF_GRE_VAL, IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (!IBRMST.CCY.equalsIgnoreCase(IBCPOSTA.CCY)) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CCY, IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBRMST.POST_CTR == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.ACT_CTR, IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, IBCQINF);
        IBCQINF.INPUT_DATA.AC_NO = IBCPOSTA.AC;
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
    }
    public void B020_01_READ_IBTMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMST);
        IBRMST.KEY.AC_NO = IBCPOSTA.AC;
        T000_READ_IBTMST();
        if (pgmRtn) return;
    }
    public void B030_CHECK_STATUS() throws IOException,SQLException,Exception {
        if (IBCQINF.OUTPUT_DATA.AC_STS != 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL, IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_PROC_VOUCHER() throws IOException,SQLException,Exception {
        if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
                B060_01_PROC_VOUCHER();
                if (pgmRtn) return;
            }
        } else {
            if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                B060_02_PROC_VOUCHER();
                if (pgmRtn) return;
            }
        }
    }
    public void B060_01_PROC_VOUCHER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCPOEWA.DATA.EVENT_CODE = K_DR_EVENT;
        BPCPOEWA.DATA.BR_OLD = IBRMST.POST_CTR;
        BPCPOEWA.DATA.BR_NEW = IBRMST.POST_CTR;
        BPCPOEWA.DATA.CI_NO = IBCQINF.OUTPUT_DATA.CI_NO;
        BPCPOEWA.DATA.AC_NO = IBRMST.ACO_AC;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = IBCPOSTA.CCY;
        if (IBCPOSTA.AMT >= 0) {
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = IBCPOSTA.AMT;
        } else {
            BPCPOEWA.DATA.AMT_INFO[11-1].AMT = IBCPOSTA.AMT;
        }
        BPCPOEWA.DATA.THEIR_AC = IBCPOSTA.OTH_AC_NO;
        BPCPOEWA.DATA.VALUE_DATE = IBCPOSTA.VAL_DATE;
        BPCPOEWA.DATA.REF_NO = IBCPOSTA.THR_REF;
        BPCPOEWA.DATA.POST_NARR = IBCPOSTA.NARR;
        BPCPOEWA.DATA.DESC = IBCPOSTA.NARR;
        BPCPOEWA.DATA.PROD_CODE = IBRMST.PROD_CD;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B060_02_PROC_VOUCHER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCBWEVT);
        BPCBWEVT.INFO.AP_MMO = "IB";
        BPCBWEVT.INFO.TR_DATE = SCCGWA.COMM_AREA.TR_DATE;
        BPCBWEVT.INFO.TR_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPCBWEVT.INFO.TR_BK = SCCGWA.COMM_AREA.TR_BANK;
        BPCBWEVT.INFO.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (SCCGWA.COMM_AREA.VCH_NO.trim().length() > 0) {
            IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.VCH_NO, BPCBWEVT.INFO.SET_NO);
        } else {
            BPCBWEVT.INFO.SET_NO.BFVCH_CD = "IB00";
        }
        CEP.TRC(SCCGWA, BPCBWEVT.INFO.SET_NO);
        BPCBWEVT.INFO.EVENT.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCBWEVT.INFO.EVENT.EVENT_CODE = K_DR_EVENT;
        BPCBWEVT.INFO.EVENT.PROD_CODE = IBRMST.PROD_CD;
        BPCBWEVT.INFO.EVENT.BR_OLD = IBRMST.POST_CTR;
        BPCBWEVT.INFO.EVENT.BR_NEW = IBRMST.POST_CTR;
        BPCBWEVT.INFO.EVENT.EVENT_CCY[1-1].CCY = IBCPOSTA.CCY;
        if (IBCPOSTA.AMT >= 0) {
            BPCBWEVT.INFO.EVENT.EVENT_AMT[1-1].AMT = IBCPOSTA.AMT;
        } else {
            BPCBWEVT.INFO.EVENT.EVENT_AMT[11-1].AMT = IBCPOSTA.AMT;
        }
        BPCBWEVT.INFO.EVENT.VAL_DATE = IBCPOSTA.VAL_DATE;
        BPCBWEVT.INFO.EVENT.AC_NO = IBRMST.ACO_AC;
        BPCBWEVT.INFO.EVENT.DESC = IBCPOSTA.NARR;
        BPCBWEVT.INFO.EVENT.POST_NARR = IBCPOSTA.NARR;
        if (IBCPOSTA.AMT != 0) {
            S000_CALL_BPZBWEVT();
            if (pgmRtn) return;
        }
    }
    public void B070_WRITE_HIST() throws IOException,SQLException,Exception {
        B070_01_GET_OTH_INFO();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.CI_NO = IBCQINF.OUTPUT_DATA.CI_NO;
        BPCPFHIS.DATA.AC = IBRMST.KEY.AC_NO;
        BPCPFHIS.DATA.ACO_AC = IBRMST.ACO_AC;
        if (IBRMST.CCY.equalsIgnoreCase("156")) {
            BPCPFHIS.DATA.TX_CCY_TYP = '1';
        } else {
            BPCPFHIS.DATA.TX_CCY_TYP = '2';
        }
        BPCPFHIS.DATA.TX_TYPE = 'T';
        BPCPFHIS.DATA.TX_CCY = IBCPOSTA.CCY;
        BPCPFHIS.DATA.TX_VAL_DT = IBCPOSTA.VAL_DATE;
        BPCPFHIS.DATA.TX_MMO = IBCPOSTA.TX_MMO;
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        BPCPFHIS.DATA.TX_AMT = IBCPOSTA.AMT;
        if (SCCGWA.COMM_AREA.REVERSAL_IND == 'Y') {
            BPCPFHIS.DATA.TX_REV_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        IBS.init(SCCGWA, IBRPHIS);
        IBRPHIS.TR_TL = SCCGWA.COMM_AREA.TL_ID;
        IBRPHIS.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRPHIS.VAL_DATE = IBCPOSTA.VAL_DATE;
        IBRPHIS.DEAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRPHIS.TR_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBRPHIS.CON_TYPE = BPCPQPRD.PRDT_MODEL;
        IBRPHIS.BCH = IBRMST.POST_CTR;
        IBRPHIS.AC[1-1] = IBRMST.NOSTRO_CODE;
        IBRPHIS.AC[2-1] = "" + 0;
        JIBS_tmp_int = IBRPHIS.AC[2-1].length();
        for (int i=0;i<0-JIBS_tmp_int;i++) IBRPHIS.AC[2-1] = "0" + IBRPHIS.AC[2-1];
        IBRPHIS.AC_NAME1 = IBCQINF.OUTPUT_DATA.AC_ENG_NAME;
        IBRPHIS.TR_SIGN = 'D';
        IBRPHIS.CCY = IBCPOSTA.CCY;
        IBRPHIS.TR_AMT = IBCPOSTA.AMT;
        IBRPHIS.BAL = 0;
        IBRPHIS.REF = IBCPOSTA.OUR_REF;
        IBRPHIS.PART.PART_OURREF = IBCPOSTA.OUR_REF;
        BPCPFHIS.DATA.REF_NO = IBCPOSTA.OUR_REF;
        IBRPHIS.THEIRREF = IBCPOSTA.THR_REF;
        IBRPHIS.PART.PART_THEIRREF = IBCPOSTA.THR_REF;
        BPCPFHIS.DATA.RLT_REF_NO = IBCPOSTA.THR_REF;
        IBRPHIS.NARR = IBCPOSTA.NARR;
        IBRPHIS.PART.PART_NARR = IBCPOSTA.NARR;
        BPCPFHIS.DATA.NARRATIVE = IBCPOSTA.NARR;
        BPCPFHIS.DATA.REMARK = IBCPOSTA.NARR;
        IBRPHIS.PART.PART_TRANID = IBCPOSTA.TXID;
        BPCPFHIS.DATA.FMT_CODE = K_OUTPUT_FMT;
        BPCPFHIS.DATA.FMT_LEN = 939;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, IBRPHIS);
        BPCPFHIS.DATA.PROD_CD = IBRMST.PROD_CD;
        BPCPFHIS.DATA.PRDMO_CD = BPCPQPRD.PRDT_MODEL;
        BPCPFHIS.DATA.RLT_AC = IBCPOSTA.OTH_AC_NO;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC);
        BPCPFHIS.DATA.RLT_BANK = "" + IBCPOSTA.OTH_BR;
        JIBS_tmp_int = BPCPFHIS.DATA.RLT_BANK.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCPFHIS.DATA.RLT_BANK = "0" + BPCPFHIS.DATA.RLT_BANK;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_BANK);
        BPCPFHIS.DATA.RLT_AC_NAME = IBCPOSTA.OTH_CNM;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC_NAME);
        BPCPFHIS.DATA.RLT_BK_NM = IBCPOSTA.OTH_BR_CNM;
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void B070_01_GET_OTH_INFO() throws IOException,SQLException,Exception {
        if (IBCPOSTA.OTH_AC_TYPE == 'N') {
            IBS.init(SCCGWA, IBCQINF);
            IBCQINF.INPUT_DATA.AC_NO = IBCPOSTA.OTH_AC_NO;
            S000_CALL_IBZQINF();
            if (pgmRtn) return;
            IBCPOSTA.OTH_BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
            IBCPOSTA.OTH_CNM = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            IBCPOSTA.OTH_BR_CNM = BPCPQORG.CHN_NM;
        }
        if (IBCPOSTA.OTH_AC_TYPE == 'I') {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = IBCPOSTA.OTH_AC_NO;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
            if (AICPQMIB.INPUT_DATA.AC == null) AICPQMIB.INPUT_DATA.AC = "";
            JIBS_tmp_int = AICPQMIB.INPUT_DATA.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AICPQMIB.INPUT_DATA.AC += " ";
            if (AICPQMIB.INPUT_DATA.AC.substring(0, 6).trim().length() == 0) IBCPOSTA.OTH_BR = 0;
            else IBCPOSTA.OTH_BR = Integer.parseInt(AICPQMIB.INPUT_DATA.AC.substring(0, 6));
            IBCPOSTA.OTH_CNM = AICPQMIB.OUTPUT_DATA.CHS_NM;
            IBS.init(SCCGWA, BPCPQORG);
            if (AICPQMIB.INPUT_DATA.AC == null) AICPQMIB.INPUT_DATA.AC = "";
            JIBS_tmp_int = AICPQMIB.INPUT_DATA.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AICPQMIB.INPUT_DATA.AC += " ";
            if (AICPQMIB.INPUT_DATA.AC.substring(0, 6).trim().length() == 0) BPCPQORG.BR = 0;
            else BPCPQORG.BR = Integer.parseInt(AICPQMIB.INPUT_DATA.AC.substring(0, 6));
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            IBCPOSTA.OTH_BR_CNM = BPCPQORG.CHN_NM;
        }
    }
    public void B080_UPD_BAL() throws IOException,SQLException,Exception {
        if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            B080_01_UPDATE_BAL();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            B080_02_UPDATE_BAL();
            if (pgmRtn) return;
        }
        if ((IBRMST.OD_FLAG == 'N' 
            || IBRMST.HLD_AMT > 0) 
            && (IBRMST.VALUE_BAL < 0 
            || IBRMST.L_VALUE_BAL < 0)) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_OD, IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, IBCBALCK);
        IBCBALCK.KEY.TYP = "PIB05";
        IBCBALCK.KEY.CD = IBRMST.PROD_CD;
        if (IBCBALCK.KEY.CD == null) IBCBALCK.KEY.CD = "";
        JIBS_tmp_int = IBCBALCK.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) IBCBALCK.KEY.CD += " ";
        if (IBRMST.CCY == null) IBRMST.CCY = "";
        JIBS_tmp_int = IBRMST.CCY.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) IBRMST.CCY += " ";
        IBCBALCK.KEY.CD = IBCBALCK.KEY.CD.substring(0, 11 - 1) + IBRMST.CCY + IBCBALCK.KEY.CD.substring(11 + 3 - 1);
        BPCPRMR.DAT_PTR = IBCBALCK;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
    }
    public void B080_01_UPDATE_BAL() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            if (SCCGWA.COMM_AREA.AC_DATE > IBRMST.LAST_FI_DATE) {
                IBRMST.L_VALUE_BAL = IBRMST.VALUE_BAL;
                IBRMST.L_HLD_AMT = IBRMST.HLD_AMT;
                IBRMST.LAST_FI_DATE = SCCGWA.COMM_AREA.AC_DATE;
            }
            if (SCCGWA.COMM_AREA.AC_DATE < IBRMST.LAST_FI_DATE) {
                IBRMST.L_VALUE_BAL = IBRMST.L_VALUE_BAL + IBCPOSTA.AMT;
                if (WS_DATA_OVERFLOW_FLAG == 'Y') {
                    IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.DATA_OVERFLOW, IBCPOSTA.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (IBRMST.L_HLD_AMT > 0) {
                    IBRMST.L_HLD_AMT = IBRMST.L_HLD_AMT + IBCPOSTA.AMT;
                }
            }
            CEP.TRC(SCCGWA, IBRMST.VALUE_BAL);
            CEP.TRC(SCCGWA, IBCPOSTA.AMT);
            IBRMST.VALUE_BAL = IBRMST.VALUE_BAL + IBCPOSTA.AMT;
            if (WS_DATA_OVERFLOW_FLAG == 'Y') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.DATA_OVERFLOW, IBCPOSTA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, IBRMST.VALUE_BAL);
        } else {
            if (SCCGWA.COMM_AREA.AC_DATE < IBRMST.LAST_FI_DATE) {
                IBRMST.L_VALUE_BAL = IBRMST.L_VALUE_BAL - IBCPOSTA.AMT;
                if (IBRMST.L_HLD_AMT > 0) {
                    IBRMST.L_HLD_AMT = IBRMST.L_HLD_AMT - IBCPOSTA.AMT;
                }
            }
            CEP.TRC(SCCGWA, IBRMST.VALUE_BAL);
            CEP.TRC(SCCGWA, IBCPOSTA.AMT);
            IBRMST.VALUE_BAL = IBRMST.VALUE_BAL - IBCPOSTA.AMT;
            CEP.TRC(SCCGWA, IBRMST.VALUE_BAL);
        }
    }
    public void B080_02_UPDATE_BAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCBATH.JPRM.NEXT_AC_DATE);
        CEP.TRC(SCCGWA, IBRMST.LAST_FI_DATE);
        if (SCCBATH.JPRM.NEXT_AC_DATB > IBRMST.LAST_FI_DATE) {
            IBRMST.VALUE_BAL = IBRMST.VALUE_BAL + IBCPOSTA.AMT;
            if (WS_DATA_OVERFLOW_FLAG == 'Y') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.DATA_OVERFLOW, IBCPOSTA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (SCCBATH.JPRM.NEXT_AC_DATB == IBRMST.LAST_FI_DATE) {
            IBRMST.L_VALUE_BAL = IBRMST.L_VALUE_BAL + IBCPOSTA.AMT;
            if (WS_DATA_OVERFLOW_FLAG == 'Y') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.DATA_OVERFLOW, IBCPOSTA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            IBRMST.VALUE_BAL = IBRMST.VALUE_BAL + IBCPOSTA.AMT;
            if (WS_DATA_OVERFLOW_FLAG == 'Y') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.DATA_OVERFLOW, IBCPOSTA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (IBRMST.AC_ATTR == 'L' 
            && IBCPOSTA.FUNC == '1') {
            B080_02_01_UPD_IBTSCASH();
            if (pgmRtn) return;
        }
    }
    public void B080_02_01_UPD_IBTSCASH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRSCASH);
        IBRSCASH.AC_NO = IBRMST.KEY.AC_NO;
        CEP.TRC(SCCGWA, IBRSCASH.AC_NO);
        T000_READ_IBTSCASH_UPD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_SCASH_FLG);
        if (IBRSCASH.UPD_DATE <= SCCBATH.JPRM.AC_DATE) {
            IBRSCASH.L_TXN_AMT = IBRSCASH.TXN_AMT;
            IBRSCASH.TXN_AMT = 0;
            IBRSCASH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBRSCASH.UPD_DATE = SCCBATH.JPRM.NEXT_AC_DATB;
            IBRSCASH.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            T000_REWRITE_IBTSCASH();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, IBRSCASH.L_TXN_AMT);
            CEP.TRC(SCCGWA, IBRSCASH.TXN_AMT);
        }
    }
    public void B090_UPD_MST() throws IOException,SQLException,Exception {
        IBRMST.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBRMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        T000_UPDATE_IBTMST();
        if (pgmRtn) return;
    }
    public void B100_WR_IBTBALF() throws IOException,SQLException,Exception {
        if (IBCPOSTA.VAL_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, IBCUBAL);
            IBCUBAL.AC_NO = IBRMST.KEY.AC_NO;
            if (IBRMST.AC_ATTR == 'L') {
                IBCUBAL.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                if (IBCPOSTA.VAL_DATE > IBRMST.L_INTS_DT) {
                    IBCUBAL.VALUE_DATE = IBCPOSTA.VAL_DATE;
                } else {
                    IBCUBAL.VALUE_DATE = IBRMST.L_INTS_DT;
                }
            }
            IBCUBAL.AMT = IBCPOSTA.AMT;
            IBCUBAL.SIGN = 'D';
            IBCUBAL.FUNC = '1';
            S000_CALL_IBZUBAL();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZUBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-UPD-BAL", IBCUBAL);
        if (IBCUBAL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCUBAL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S00_CALL_AIZCKOP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-CHK-OPEN-PERIOD", AICOCKOP);
        if (AICOCKOP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICOCKOP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZBWEVT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BAT-EVENT", BPCBWEVT);
        if (BPCBWEVT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCBWEVT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_LINK_SCSOCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = IBCPOSTA.CCY;
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZCBSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-CHECK-BAT-STATUS", SCCCBSTS);
        if (SCCCBSTS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCCBSTS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZWRMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0 
            && BPCPRMR.RC.RC_RTNCODE != 180) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBS.READ(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_REC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPD_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBTMST_RD.upd = true;
        IBS.READ(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_REC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBS.REWRITE(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_IBTMST_2() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBTMST_RD.fst = true;
        IBTMST_RD.where = "NOSTRO_CODE = :IBRMST.NOSTRO_CODE "
            + "AND CCY = :IBRMST.CCY";
        IBS.READ(SCCGWA, IBRMST, this, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST, IBCPOSTA.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTSCASH_UPD() throws IOException,SQLException,Exception {
        IBTSCASH_RD = new DBParm();
        IBTSCASH_RD.TableName = "IBTSCASH";
        IBTSCASH_RD.where = "AC_NO = :IBRSCASH.AC_NO";
        IBTSCASH_RD.upd = true;
        IBS.READ(SCCGWA, IBRSCASH, this, IBTSCASH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SCASH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_SCASH_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTSCASH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_IBTSCASH() throws IOException,SQLException,Exception {
        IBTSCASH_RD = new DBParm();
        IBTSCASH_RD.TableName = "IBTSCASH";
        IBS.REWRITE(SCCGWA, IBRSCASH, IBTSCASH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SCASH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_SCASH_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTSCASH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
        CEP.TRC(SCCGWA, IBCPOSTA.RC);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (IBCPOSTA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "IBCPOSTA = ");
            CEP.TRC(SCCGWA, IBCPOSTA);
        }
    } //FROM #ENDIF
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
