package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICACCU;
import com.hisun.DD.DDCIMMST;
import com.hisun.DD.DDCUDRAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZSBVSO {
    boolean pgmRtn = false;
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String CPN_U_INQ_MST_INF = "DD-U-INQ-MST-INF";
    String CPN_DD_NFIN_M_MST = "DD-I-NFIN-M-MST";
    String CPN_U_BVUSE = "BP-U-TLR-BV-USE     ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_MGM_CUS_BV = "BP-R-MGM-BCUS";
    String CPN_QUERY_TLR = "BP-F-TLR-INF-QUERY  ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_F_F_CAL_FEE = "BP-F-F-CAL-FEE      ";
    String CPN_R_MGM_BCUS = "BP-R-MGM-BCUS       ";
    String CPN_R_MGM_BHIS = "BP-R-MGM-BHIS       ";
    String CPN_R_BRW_BCUS = "BP-R-BRW-BCUS       ";
    String CPN_R_MGM_BUSE = "BP-R-MGM-BUSE       ";
    String CPN_S_BV_SELL = "BP-S-BV-SELL-OUT ";
    String K_OUTPUT_FMT = "BP160";
    short K_CNT = 18;
    String CPN_REV_DD_DDZUDRAC = "DD-UNIT-DRAW-PROC   ";
    String CPN_REV_DD_DDZURDRW = "DD-UNIT-REV-DRW";
    String CPN_CALL_CASH_ADD_BOX = "BP-U-ADD-CBOX  ";
    String CPN_CALL_CASH_SUB_BOX = "BP-U-SUB-CBOX  ";
    String CPN_F_INQ_BV_ACT = "BP-F-INQ-BV-ACT";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_NUM = 0;
    long WS_NOW_NO = 0;
    long WS_BEG_NO = 0;
    long WS_END_NO = 0;
    String WS_STORAGE = " ";
    String WS_APP_CI_NO = " ";
    double WS_COST_PRICE_ALL = 0;
    double WS_SER_CHARGE_ALL = 0;
    double WS_PT_AMT = 0;
    BPZSBVSO_WS_EWA_AC_NO WS_EWA_AC_NO = new BPZSBVSO_WS_EWA_AC_NO();
    String BPZSBVSO_FILLER1 = "BV CODE:";
    String WS_HIS_BVCODE = " ";
    String BPZSBVSO_FILLER3 = "HEAD NO:";
    String WS_HIS_HEADNO = " ";
    String BPZSBVSO_FILLER5 = "BEG NO:";
    String WS_HIS_BEGNO = " ";
    String BPZSBVSO_FILLER7 = "END NO:";
    String WS_HIS_ENDNO = " ";
    String BPZSBVSO_FILLER9 = "NUM NO:";
    int WS_HIS_NUMNO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCO160 BPCO160 = new BPCO160();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPRBHIS BPRBHIS = new BPRBHIS();
    BPRBUSE BPRBUSE = new BPRBUSE();
    BPRBCUS BPRBCUS = new BPRBCUS();
    DDCIMMST DDCIMMST = new DDCIMMST();
    BPCRBCUS BPCRBCUS = new BPCRBCUS();
    BPCRBHIS BPCRBHIS = new BPCRBHIS();
    BPCRBCUB BPCRBCUB = new BPCRBCUB();
    BPCRBUSE BPCRBUSE = new BPCRBUSE();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICACCU CICACCU = new CICACCU();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    BPCUABOX BPCUABOX = new BPCUABOX();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    SCCGWA SCCGWA;
    BPCGCFEE BPCGCFEE;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSBVSO BPCSBVSO;
    public void MP(SCCGWA SCCGWA, BPCSBVSO BPCSBVSO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBVSO = BPCSBVSO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBVSO return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCGCFEE = new BPCGCFEE();
        IBS.init(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, GWA_BP_AREA.FEE_AREA.FEE_DATA_PTR, BPCGCFEE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_VOUCHER();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (BPCSBVSO.PAY_TYPE == '1') {
                B020_CHECK_AC();
                if (pgmRtn) return;
            }
            B030_BV_OUT_PROC();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            } else {
                B040_BV_TO_CLIENT_PROC();
                if (pgmRtn) return;
            }
            B060_ADD_BUSE_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCSBVSO.FLG);
            if (BPCSBVSO.FLG == 'Y') {
                if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                    B070_BV_SALE_CH();
                    if (pgmRtn) return;
                } else {
                    B070_BV_SALE();
                    if (pgmRtn) return;
                }
            }
            B080_REC_NHIS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            B030_BV_OUT_PROC();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            } else {
                B049_BV_TO_CLIENT_CANCEL();
                if (pgmRtn) return;
            }
            B069_ADD_BUSE_PROC_CANCEL();
            if (pgmRtn) return;
            if (BPCFBVQU.TX_DATA.COST_PRICE > 0 
                || BPCFBVQU.TX_DATA.SER_CHARGE > 0 
                && BPCSBVSO.FLG == 'Y') {
                if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                    B070_BV_SALE_CH();
                    if (pgmRtn) return;
                } else {
                    B070_BV_SALE();
                    if (pgmRtn) return;
                }
            }
            B080_REC_NHIS();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANCEL_NO_TERTIAN;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_CHECK_VOUCHER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.TYPE = '0';
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVSO.CODE;
        S000_CALL_BVZFBVQU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.COST_PRICE);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.SER_CHARGE);
        if (BPCFBVQU.TX_DATA.SELL_FLG == 'N' 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_CANT_SELL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!IBS.isNumeric(BPCFBVQU.TX_DATA.COST_PRICE+"")) {
            BPCFBVQU.TX_DATA.COST_PRICE = 0;
        }
        if (!IBS.isNumeric(BPCFBVQU.TX_DATA.SER_CHARGE+"")) {
            BPCFBVQU.TX_DATA.SER_CHARGE = 0;
        }
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
    }
    public void B020_CHECK_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = BPCSBVSO.APP_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        WS_APP_CI_NO = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, BPCSBVSO.APP_AC);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        if (BPCSBVSO.PAY_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = BPCSBVSO.PAY_AC;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCSBVSO.PAY_AC);
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
            if (!WS_APP_CI_NO.equalsIgnoreCase(CICACCU.DATA.CI_NO)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_APP_PAY_CI_DIF;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_BV_OUT_PROC() throws IOException,SQLException,Exception {
        if (BPCSBVSO.COUNT_MTH == '0') {
            if (BPCSBVSO.BEG_NO == null) BPCSBVSO.BEG_NO = "";
            JIBS_tmp_int = BPCSBVSO.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSBVSO.BEG_NO += " ";
            for (WS_CNT = 1; IBS.isNumeric(BPCSBVSO.BEG_NO.substring(WS_CNT - 1, WS_CNT + 1 - 1)); WS_CNT += 1) {
            }
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
            CEP.TRC(SCCGWA, WS_CNT);
            if (BPCSBVSO.BEG_NO == null) BPCSBVSO.BEG_NO = "";
            JIBS_tmp_int = BPCSBVSO.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSBVSO.BEG_NO += " ";
            if (BPCSBVSO.BEG_NO.substring(WS_CNT - 1, WS_CNT + 1 - 1).trim().length() > 0 
                || BPCFBVQU.TX_DATA.NO_LENGTH != WS_CNT - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSBVSO.BEG_NO == null) BPCSBVSO.BEG_NO = "";
            JIBS_tmp_int = BPCSBVSO.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSBVSO.BEG_NO += " ";
            if (BPCSBVSO.BEG_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH).trim().length() == 0) WS_BEG_NO = 0;
            else WS_BEG_NO = Long.parseLong(BPCSBVSO.BEG_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH));
            WS_NUM = BPCSBVSO.PAGE_NUM * BPCSBVSO.BUY_NUM;
            WS_END_NO = WS_BEG_NO + WS_NUM - 1;
            WS_STORAGE = "" + WS_END_NO;
            JIBS_tmp_int = WS_STORAGE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE = "0" + WS_STORAGE;
            WS_CNT = K_CNT - BPCFBVQU.TX_DATA.NO_LENGTH + 1;
            if (WS_STORAGE == null) WS_STORAGE = "";
            JIBS_tmp_int = WS_STORAGE.length();
            for (int i=0;i<18-JIBS_tmp_int;i++) WS_STORAGE += " ";
            if (BPCSBVSO.END_NO == null) BPCSBVSO.END_NO = "";
            JIBS_tmp_int = BPCSBVSO.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSBVSO.END_NO += " ";
            BPCSBVSO.END_NO = WS_STORAGE.substring(WS_CNT - 1, WS_CNT + BPCFBVQU.TX_DATA.NO_LENGTH - 1) + BPCSBVSO.END_NO.substring(BPCFBVQU.TX_DATA.NO_LENGTH);
            BPCSBVSO.NUM = WS_NUM;
        }
        IBS.init(SCCGWA, BPCUBUSE);
        BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBUSE.VB_FLG = '0';
        BPCUBUSE.TYPE = '0';
        BPCUBUSE.BV_CODE = BPCSBVSO.CODE;
        BPCUBUSE.COUNT_MTH = BPCSBVSO.COUNT_MTH;
        BPCUBUSE.BEG_NO = BPCSBVSO.BEG_NO;
        BPCUBUSE.END_NO = BPCSBVSO.END_NO;
        BPCUBUSE.NUM = BPCSBVSO.NUM;
        BPCUBUSE.PAGE_NUM = BPCSBVSO.PAGE_NUM;
        BPCUBUSE.BUY_NUM = BPCSBVSO.BUY_NUM;
        CEP.TRC(SCCGWA, BPCUBUSE.BEG_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.END_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.NUM);
        CEP.TRC(SCCGWA, BPCSBVSO.BUY_NUM);
        BPCUBUSE.CCY = BPCSBVSO.PAY_CCY;
        CEP.TRC(SCCGWA, BPCUBUSE.CCY);
        S000_CALL_BPZUBUSE();
        if (pgmRtn) return;
    }
    public void B040_BV_TO_CLIENT_PROC() throws IOException,SQLException,Exception {
        if (BPCFBVQU.TX_DATA.CTL_FLG != '0') {
            R010_CHECK_IF_HAVE();
            if (pgmRtn) return;
        }
        R020_INSERT_INTO_BHIS();
        if (pgmRtn) return;
        if (BPCFBVQU.TX_DATA.CTL_FLG != '0') {
            R030_INSERT_INTO_BCUS();
            if (pgmRtn) return;
        }
    }
    public void B049_BV_TO_CLIENT_CANCEL() throws IOException,SQLException,Exception {
        if (BPCFBVQU.TX_DATA.CTL_FLG != '0') {
            R010_BCUS_CANCEL_PROC();
            if (pgmRtn) return;
        }
        R029_BHIS_CANCEL_PROC();
        if (pgmRtn) return;
    }
    public void B070_BV_SALE() throws IOException,SQLException,Exception {
        if (BPCSBVSO.PAY_TYPE == '0') {
            R000_SALE_CASH();
            if (pgmRtn) return;
        } else {
            if (BPCSBVSO.PAY_TYPE == '1') {
                R000_SALE_AC();
                if (pgmRtn) return;
            }
        }
    }
    public void B070_BV_SALE_CH() throws IOException,SQLException,Exception {
        if (BPCSBVSO.PAY_TYPE == '0') {
            R000_SALE_CASH_CH();
            if (pgmRtn) return;
        } else {
            if (BPCSBVSO.PAY_TYPE == '1') {
                R000_SALE_AC_CH();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_SALE_CASH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        JIBS_NumStr = "" + 0;
        BPCFFTXI.TX_DATA.AUH_FLG = JIBS_NumStr.charAt(0);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '1';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = BPCSBVSO.PAY_CCY;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.TX_CCY = BPCSBVSO.PAY_CCY;
        BPCTCALF.INPUT_AREA.TX_CNT = (short) BPCSBVSO.NUM;
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].CHG_AMT);
    }
    public void R000_SALE_CASH_CH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        JIBS_NumStr = "" + 0;
        BPCFFTXI.TX_DATA.AUH_FLG = JIBS_NumStr.charAt(0);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '1';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = BPCSBVSO.PAY_CCY;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.TX_CCY = BPCSBVSO.PAY_CCY;
        BPCTCALF.INPUT_AREA.TX_CNT = (short) BPCSBVSO.NUM;
        BPCTCALF.INPUT_AREA.ATTR_VAL.BVF_TYPE_FLG = BPCSBVSO.FEE_BVTY;
        BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].CHG_AMT);
    }
    public void R000_TRANS_BPCUABOX_IN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUABOX);
        BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUABOX.CCY = BPCSBVSO.PAY_CCY;
        BPCUABOX.OPP_AC = BPCSBVSO.APP_AC;
        CEP.TRC(SCCGWA, BPCSBVSO.NUM);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.COST_PRICE);
        WS_COST_PRICE_ALL = BPCSBVSO.NUM * BPCFBVQU.TX_DATA.COST_PRICE;
        WS_SER_CHARGE_ALL = BPCSBVSO.NUM * BPCFBVQU.TX_DATA.SER_CHARGE;
        BPCUABOX.AMT = WS_COST_PRICE_ALL + WS_SER_CHARGE_ALL;
        CEP.TRC(SCCGWA, WS_COST_PRICE_ALL);
        CEP.TRC(SCCGWA, WS_SER_CHARGE_ALL);
        CEP.TRC(SCCGWA, BPCUABOX.AMT);
        BPCUABOX.CASH_NO = BPCSBVSO.CASH_CD;
    }
    public void S000_CALL_SALE_CASH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALL_CASH_ADD_BOX, BPCUABOX);
        if (BPCUABOX.RTNCODE != 0) {
            WS_ERR_MSG = "" + BPCUABOX.RTNCODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_BPCUSBOX_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUSBOX);
        BPCUSBOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUSBOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUSBOX.CCY = BPCSBVSO.PAY_CCY;
        BPCUSBOX.AMT = BPCSBVSO.NUM * BPCFBVQU.TX_DATA.COST_PRICE;
        BPCUSBOX.CASH_NO = BPCSBVSO.CASH_CD;
    }
    public void S000_CALL_SALE_SUB_CASH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALL_CASH_SUB_BOX, BPCUSBOX);
    }
    public void R000_SALE_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        JIBS_NumStr = "" + 0;
        BPCFFTXI.TX_DATA.AUH_FLG = JIBS_NumStr.charAt(0);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = BPCSBVSO.PAY_AC;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = BPCSBVSO.PAY_CCY;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.TX_CCY = BPCSBVSO.PAY_CCY;
        BPCTCALF.INPUT_AREA.TX_CNT = (short) BPCSBVSO.NUM;
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].CHG_AMT);
    }
    public void R000_SALE_AC_CH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        JIBS_NumStr = "" + 0;
        BPCFFTXI.TX_DATA.AUH_FLG = JIBS_NumStr.charAt(0);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = BPCSBVSO.PAY_AC;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = BPCSBVSO.PAY_CCY;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.TX_CCY = BPCSBVSO.PAY_CCY;
        BPCTCALF.INPUT_AREA.TX_CNT = (short) BPCSBVSO.NUM;
        BPCTCALF.INPUT_AREA.ATTR_VAL.BVF_TYPE_FLG = BPCSBVSO.FEE_BVTY;
        BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].CHG_AMT);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REV_DD_DDZUDRAC, DDCUDRAC);
    }
    public void B075_CALL_BVFSALE() throws IOException,SQLException,Exception {
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        T000_01_SET_EWA_BASIC_INF();
        if (pgmRtn) return;
        T000_02_SET_EWA_EVENTS();
        if (pgmRtn) return;
    }
    public void T000_01_SET_EWA_BASIC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_02_SET_EWA_EVENTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "BVF";
        BPCPOEWA.DATA.EVENT_CODE = "BVFSALE";
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCSBVSO.PAY_CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCSBVSO.NUM * BPCFBVQU.TX_DATA.COST_PRICE;
        BPCPOEWA.DATA.AMT_INFO[2-1].AMT = BPCSBVSO.NUM * BPCFBVQU.TX_DATA.SER_CHARGE;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_EWA_AC_NO.WS_BACT_CODE = BPCSBVSO.CODE;
        WS_EWA_AC_NO.WS_BACT_STAT = '0';
        WS_EWA_AC_NO.WS_BACT_BV_KIND = 'N';
        BPCPOEWA.DATA.AC_NO = IBS.CLS2CPY(SCCGWA, WS_EWA_AC_NO);
        BPCPOEWA.DATA.PORT = BPCSBVSO.CODE;
        BPCPOEWA.DATA.PROD_CODE = BPCSBVSO.CODE;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B060_ADD_BUSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVSO.CODE;
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
        if (BPCFBVQU.TX_DATA.TYPE == '0' 
            && BPCFBVQU.TX_DATA.CTL_FLG != '0') {
            IBS.init(SCCGWA, BPRBUSE);
            IBS.init(SCCGWA, BPCRBUSE);
            BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRBUSE.KEY.BV_CODE = BPCSBVSO.CODE;
            BPRBUSE.KEY.HEAD_NO = BPCSBVSO.HEAD_NO;
            BPRBUSE.KEY.BEG_NO = BPCSBVSO.BEG_NO;
            BPRBUSE.KEY.END_NO = BPCSBVSO.END_NO;
            BPRBUSE.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
            BPRBUSE.TYPE = '0';
            BPRBUSE.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            BPRBUSE.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRBUSE.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRBUSE.TX_AUTH = " ";
            BPRBUSE.STS = '3';
            BPRBUSE.REC_STS = '0';
            BPCRBUSE.INFO.FUNC = 'A';
            S000_CALL_BPZRBUSE();
            if (pgmRtn) return;
        }
    }
    public void B069_ADD_BUSE_PROC_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVSO.CODE;
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
        if (BPCFBVQU.TX_DATA.TYPE == '0' 
            && BPCFBVQU.TX_DATA.CTL_FLG != '0') {
            IBS.init(SCCGWA, BPRBUSE);
            IBS.init(SCCGWA, BPCRBUSE);
            BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRBUSE.KEY.BV_CODE = BPCSBVSO.CODE;
            BPRBUSE.KEY.HEAD_NO = BPCSBVSO.HEAD_NO;
            BPRBUSE.KEY.BEG_NO = BPCSBVSO.BEG_NO;
            BPRBUSE.KEY.END_NO = BPCSBVSO.END_NO;
            BPRBUSE.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
            BPCRBUSE.INFO.FUNC = 'R';
            S000_CALL_BPZRBUSE();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCRBUSE);
            BPRBUSE.REC_STS = '1';
            BPCRBUSE.INFO.FUNC = 'U';
            S000_CALL_BPZRBUSE();
            if (pgmRtn) return;
        }
    }
    public void B080_REC_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.TX_TYP_CD = "01";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_HIS_BVCODE = BPCSBVSO.CODE;
        WS_HIS_HEADNO = BPCSBVSO.HEAD_NO;
        WS_HIS_BEGNO = BPCSBVSO.BEG_NO;
        WS_HIS_ENDNO = BPCSBVSO.END_NO;
        WS_HIS_NUMNO = BPCSBVSO.NUM;
        BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, BPZSBVSO_WS2);
        BPCPNHIS.INFO.FMT_ID = "BPCSBVSO";
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R090_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO160;
        SCCFMT.DATA_LEN = 3886;
        CEP.TRC(SCCGWA, BPCO160);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_AC_PROD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMMST);
        DDCIMMST.TX_TYPE = 'I';
        DDCIMMST.DATA.KEY.AC_NO = BPCSBVSO.PAY_AC;
        CEP.TRC(SCCGWA, BPCSBVSO.PAY_AC);
        CEP.TRC(SCCGWA, DDCIMMST.DATA.KEY.AC_NO);
        S000_CALL_DDZIMMST();
        if (pgmRtn) return;
    }
    public void R001_GET_AC_PROD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMMST);
        DDCIMMST.TX_TYPE = 'I';
        DDCIMMST.DATA.KEY.AC_NO = BPCSBVSO.APP_AC;
        S000_CALL_DDZIMMST();
        if (pgmRtn) return;
    }
    public void R010_CHECK_IF_HAVE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "AAAAA");
        CEP.TRC(SCCGWA, BPCSBVSO.BEG_NO);
        CEP.TRC(SCCGWA, BPCSBVSO.END_NO);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
        CEP.TRC(SCCGWA, WS_BEG_NO);
        CEP.TRC(SCCGWA, WS_NOW_NO);
        CEP.TRC(SCCGWA, WS_END_NO);
        if (BPCSBVSO.BEG_NO == null) BPCSBVSO.BEG_NO = "";
        JIBS_tmp_int = BPCSBVSO.BEG_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSBVSO.BEG_NO += " ";
        if (BPCSBVSO.BEG_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH).trim().length() == 0) WS_BEG_NO = 0;
        else WS_BEG_NO = Long.parseLong(BPCSBVSO.BEG_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH));
        if (BPCSBVSO.BEG_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH).trim().length() == 0) WS_NOW_NO = 0;
        else WS_NOW_NO = Long.parseLong(BPCSBVSO.BEG_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH));
        if (BPCSBVSO.END_NO == null) BPCSBVSO.END_NO = "";
        JIBS_tmp_int = BPCSBVSO.END_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSBVSO.END_NO += " ";
        if (BPCSBVSO.END_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH).trim().length() == 0) WS_END_NO = 0;
        else WS_END_NO = Long.parseLong(BPCSBVSO.END_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH));
        while (WS_NOW_NO <= WS_END_NO) {
            CEP.TRC(SCCGWA, "BBBBB");
            IBS.init(SCCGWA, BPRBCUS);
            IBS.init(SCCGWA, BPCRBCUB);
            BPCRBCUB.INFO.FUNC = '1';
            BPRBCUS.KEY.BV_CODE = BPCSBVSO.CODE;
            BPRBCUS.KEY.BEG_NO = "" + WS_NOW_NO;
            JIBS_tmp_int = BPRBCUS.KEY.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPRBCUS.KEY.BEG_NO = "0" + BPRBCUS.KEY.BEG_NO;
            BPRBCUS.KEY.END_NO = "" + WS_NOW_NO;
            JIBS_tmp_int = BPRBCUS.KEY.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPRBCUS.KEY.END_NO = "0" + BPRBCUS.KEY.END_NO;
            BPRBCUS.KEY.AC = BPCSBVSO.APP_AC;
            S000_CALL_BPZRBCUB();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCRBCUB);
            BPCRBCUB.INFO.FUNC = 'N';
            S000_CALL_BPZRBCUB();
            if (pgmRtn) return;
            if (BPCRBCUB.RETURN_INFO == 'F') {
                if (BPRBCUS.KEY.BEG_NO == null) BPRBCUS.KEY.BEG_NO = "";
                JIBS_tmp_int = BPRBCUS.KEY.BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPRBCUS.KEY.BEG_NO += " ";
                if (BPRBCUS.KEY.BEG_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH).trim().length() == 0) WS_BEG_NO = 0;
                else WS_BEG_NO = Long.parseLong(BPRBCUS.KEY.BEG_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH));
                WS_NUM = (int) (WS_NOW_NO - WS_BEG_NO + 1);
