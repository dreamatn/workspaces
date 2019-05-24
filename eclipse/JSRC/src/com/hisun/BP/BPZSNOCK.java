package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSNOCK {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    char K_STS_NORMAL = '0';
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY";
    String CPN_DCZFCDGG = "DC-F-CHK-DIGIT-GEN";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_J = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    int WS_TOTAL_NUM = 0;
    int WS_CNT = 0;
    long WS_BEG_NO = 0;
    long WS_END_NO = 0;
    char WS_CHKNO = ' ';
    short WS_NOLEN = 0;
    short WS_NOLENTH = 0;
    String WS_COMP_BEG = " ";
    String WS_COMP_END = " ";
    short WS_FLD_NO = 0;
    String WS_OUT_END = " ";
    int WS_POS = 0;
    long WS_BV_NO = 0;
    long WS_PRE_NO = 0;
    long WS_NEXT_NO = 0;
    String WS_COMP_BV = " ";
    String WS_COMP_PRE = " ";
    short WS_STR_POS = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    DCCFCDGG DCCFCDGG = new DCCFCDGG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTLT BPRTLT;
    BPCSNOCK BPCSNOCK;
    public void MP(SCCGWA SCCGWA, BPCSNOCK BPCSNOCK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSNOCK = BPCSNOCK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSNOCK return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSNOCK);
        CEP.TRC(SCCGWA, BPCSNOCK.FUNC);
        CEP.TRC(SCCGWA, BPCSNOCK.BV_CODE);
        CEP.TRC(SCCGWA, BPCSNOCK.BEG_NO);
        CEP.TRC(SCCGWA, BPCSNOCK.END_NO);
        CEP.TRC(SCCGWA, BPCSNOCK.NUM);
        CEP.TRC(SCCGWA, BPCSNOCK.BV_NO);
        B010_CHK_INPUT();
        if (BPCSNOCK.FUNC == '1'
            || BPCSNOCK.FUNC == '2'
            || BPCSNOCK.FUNC == '3') {
            B020_COUNT_BV_NUM();
        } else if (BPCSNOCK.FUNC == '4'
            || BPCSNOCK.FUNC == '5') {
            B030_FND_NEXT_PRE();
        }
        B070_DATA_OUTPUT();
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSNOCK.BV_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            if (BPCSNOCK.BV_CODE.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BPCSNOCK.BV_CODE);
            S000_ERR_MSG_PROC();
        }
        if ((BPCSNOCK.BEG_NO.trim().length() == 0 
            && BPCSNOCK.BV_NO.trim().length() == 0) 
            || (BPCSNOCK.BEG_NO.trim().length() > 0 
            && BPCSNOCK.BV_NO.trim().length() > 0)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSNOCK.BV_CODE;
        S000_CALL_BPZFBVQU();
        CEP.TRC(SCCGWA, "NCB032002");
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.CHK_FLG);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.STR_POS);
        WS_STR_POS = BPCFBVQU.TX_DATA.STR_POS;
        if (BPCSNOCK.BEG_NO.trim().length() > 0 
            && BPCFBVQU.TX_DATA.CHK_FLG != ' ') {
            if (BPCSNOCK.BEG_NO == null) BPCSNOCK.BEG_NO = "";
            JIBS_tmp_int = BPCSNOCK.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BEG_NO += " ";
            for (WS_I = WS_STR_POS; WS_I <= 20 
                && IBS.isNumeric(BPCSNOCK.BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                if (BPCSNOCK.BEG_NO == null) BPCSNOCK.BEG_NO = "";
                JIBS_tmp_int = BPCSNOCK.BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BEG_NO += " ";
                if (BPCFBVQU.TX_DATA.CHK_FLG == BPCSNOCK.BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_CHK_FLG_ERR;
                    if (BPCSNOCK.BEG_NO.trim().length() == 0) WS_FLD_NO = 0;
                    else WS_FLD_NO = Short.parseShort(BPCSNOCK.BEG_NO);
                    S000_ERR_MSG_PROC();
                }
            }
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
            CEP.TRC(SCCGWA, WS_I);
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                CEP.TRC(SCCGWA, "XGQ1");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                if (BPCSNOCK.BEG_NO.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(BPCSNOCK.BEG_NO);
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSNOCK.END_NO.trim().length() > 0 
            && BPCFBVQU.TX_DATA.CHK_FLG != ' ') {
            if (BPCSNOCK.END_NO == null) BPCSNOCK.END_NO = "";
            JIBS_tmp_int = BPCSNOCK.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.END_NO += " ";
            for (WS_I = WS_STR_POS; WS_I <= 20 
                && IBS.isNumeric(BPCSNOCK.END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                if (BPCSNOCK.END_NO == null) BPCSNOCK.END_NO = "";
                JIBS_tmp_int = BPCSNOCK.END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.END_NO += " ";
                if (BPCFBVQU.TX_DATA.CHK_FLG == BPCSNOCK.END_NO.substring(WS_I - 1, WS_I + 1 - 1)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_END_CHK_FLG_ERR;
                    if (BPCSNOCK.BEG_NO.trim().length() == 0) WS_FLD_NO = 0;
                    else WS_FLD_NO = Short.parseShort(BPCSNOCK.BEG_NO);
                    S000_ERR_MSG_PROC();
                }
            }
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
            CEP.TRC(SCCGWA, WS_I);
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                CEP.TRC(SCCGWA, "XGQ2");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                if (BPCSNOCK.END_NO.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(BPCSNOCK.END_NO);
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSNOCK.BV_NO.trim().length() > 0 
            && BPCFBVQU.TX_DATA.CHK_FLG != ' ') {
            if (BPCSNOCK.BV_NO == null) BPCSNOCK.BV_NO = "";
            JIBS_tmp_int = BPCSNOCK.BV_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BV_NO += " ";
            for (WS_I = WS_STR_POS; WS_I <= 20 
                && IBS.isNumeric(BPCSNOCK.BV_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                if (BPCSNOCK.BV_NO == null) BPCSNOCK.BV_NO = "";
                JIBS_tmp_int = BPCSNOCK.BV_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BV_NO += " ";
                if (BPCFBVQU.TX_DATA.CHK_FLG == BPCSNOCK.BV_NO.substring(WS_I - 1, WS_I + 1 - 1)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_CHK_FLG_ERR;
                    if (BPCSNOCK.BEG_NO.trim().length() == 0) WS_FLD_NO = 0;
                    else WS_FLD_NO = Short.parseShort(BPCSNOCK.BEG_NO);
                    S000_ERR_MSG_PROC();
                }
            }
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
            CEP.TRC(SCCGWA, WS_I);
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                CEP.TRC(SCCGWA, "XGQ3");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                if (BPCSNOCK.BEG_NO.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(BPCSNOCK.BEG_NO);
                S000_ERR_MSG_PROC();
            }
        }
        WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
        if ((BPCFBVQU.TX_DATA.CTL_FLG == '1' 
            || BPCFBVQU.TX_DATA.CTL_FLG == '2') 
            && (BPCSNOCK.END_NO.trim().length() > 0 
            && BPCSNOCK.NUM != 0)) {
            if (BPCSNOCK.BEG_NO == null) BPCSNOCK.BEG_NO = "";
            JIBS_tmp_int = BPCSNOCK.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BEG_NO += " ";
            if (BPCSNOCK.BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
            else WS_COMP_BEGNO = Long.parseLong(BPCSNOCK.BEG_NO.substring(0, WS_BVNO_LEN));
            if (BPCSNOCK.END_NO == null) BPCSNOCK.END_NO = "";
            JIBS_tmp_int = BPCSNOCK.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.END_NO += " ";
            if (BPCSNOCK.END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
            else WS_COMP_ENDNO = Long.parseLong(BPCSNOCK.END_NO.substring(0, WS_BVNO_LEN));
            if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                if (BPCSNOCK.BEG_NO.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(BPCSNOCK.BEG_NO);
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, BPCSNOCK.NUM);
            CEP.TRC(SCCGWA, BPCSNOCK.BEG_NO);
            CEP.TRC(SCCGWA, BPCSNOCK.END_NO);
            if (BPCFBVQU.TX_DATA.CHK_FLG == ' ' 
                && (WS_COMP_BEGNO == 0 
                || WS_COMP_ENDNO == 0 
                || BPCSNOCK.NUM != WS_COMP_ENDNO - WS_COMP_BEGNO + 1 )) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END_NUM;
                if (BPCSNOCK.END_NO.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(BPCSNOCK.END_NO);
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCFBVQU.TX_DATA.CHK_FLG != ' ' 
            && BPCFBVQU.TX_DATA.BV_CFLG == 'Y') {
            IBS.init(SCCGWA, DCCFCDGG);
            WS_NOLENTH = (short) (WS_BVNO_LEN + 1);
            if (BPCSNOCK.BEG_NO.trim().length() > 0) {
                if (BPCSNOCK.BEG_NO == null) BPCSNOCK.BEG_NO = "";
                JIBS_tmp_int = BPCSNOCK.BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BEG_NO += " ";
                DCCFCDGG.VAL.CARD_NO = BPCSNOCK.BEG_NO.substring(0, WS_BVNO_LEN);
            } else {
                if (BPCSNOCK.BV_NO == null) BPCSNOCK.BV_NO = "";
                JIBS_tmp_int = BPCSNOCK.BV_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BV_NO += " ";
                DCCFCDGG.VAL.CARD_NO = BPCSNOCK.BV_NO.substring(0, WS_BVNO_LEN);
            }
            CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
            DCCFCDGG.VAL.FUNC = 'G';
            S000_CALL_DCZFCDGG();
            CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO_GEN);
            CEP.TRC(SCCGWA, WS_NOLEN);
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.CHK_FLG);
            if (DCCFCDGG.VAL.CARD_NO_GEN == null) DCCFCDGG.VAL.CARD_NO_GEN = "";
            JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO_GEN.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO_GEN += " ";
            if (DCCFCDGG.VAL.CARD_NO_GEN.substring(WS_NOLENTH - 1, WS_NOLENTH + 1 - 1).equalsIgnoreCase(BPCFBVQU.TX_DATA.CHK_FLG+"")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_BV_INPUT_ERR;
                if (DCCFCDGG.VAL.CARD_NO_GEN.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(DCCFCDGG.VAL.CARD_NO_GEN);
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCFBVQU.TX_DATA.CHK_FLG != ' ' 
            && BPCFBVQU.TX_DATA.BV_CFLG == 'Y') {
            if (BPCSNOCK.END_NO.trim().length() > 0) {
                IBS.init(SCCGWA, DCCFCDGG);
                WS_NOLENTH = (short) (WS_BVNO_LEN + 1);
                if (BPCSNOCK.END_NO == null) BPCSNOCK.END_NO = "";
                JIBS_tmp_int = BPCSNOCK.END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.END_NO += " ";
                DCCFCDGG.VAL.CARD_NO = BPCSNOCK.END_NO.substring(0, WS_BVNO_LEN);
                CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
                DCCFCDGG.VAL.FUNC = 'G';
                S000_CALL_DCZFCDGG();
                CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO_GEN);
                CEP.TRC(SCCGWA, WS_NOLEN);
                CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.CHK_FLG);
                if (DCCFCDGG.VAL.CARD_NO_GEN == null) DCCFCDGG.VAL.CARD_NO_GEN = "";
                JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO_GEN.length();
                for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO_GEN += " ";
                if (DCCFCDGG.VAL.CARD_NO_GEN.substring(WS_NOLENTH - 1, WS_NOLENTH + 1 - 1).equalsIgnoreCase(BPCFBVQU.TX_DATA.CHK_FLG+"")) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_END_NO_INPUT_ERR;
                    if (DCCFCDGG.VAL.CARD_NO_GEN.trim().length() == 0) WS_FLD_NO = 0;
                    else WS_FLD_NO = Short.parseShort(DCCFCDGG.VAL.CARD_NO_GEN);
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
        }
    }
    public void B020_COUNT_BV_NUM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NCB032005");
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.CHK_FLG);
        if (BPCFBVQU.TX_DATA.CHK_FLG == ' ') {
            B030_END_AND_NUM();
        } else {
            if (BPCSNOCK.NUM == 0) {
                CEP.TRC(SCCGWA, "XGQ1");
                B040_END_TO_NUM_Y();
            } else {
                CEP.TRC(SCCGWA, "XGQ2");
                B050_NUM_TO_END_Y();
            }
        }
        B060_CHECK_BV_NUM();
    }
    public void B030_END_AND_NUM() throws IOException,SQLException,Exception {
        if (BPCSNOCK.NUM == 0) {
            if (BPCSNOCK.BEG_NO == null) BPCSNOCK.BEG_NO = "";
            JIBS_tmp_int = BPCSNOCK.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BEG_NO += " ";
            if (BPCSNOCK.BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_BEG_NO = 0;
            else WS_BEG_NO = Long.parseLong(BPCSNOCK.BEG_NO.substring(0, WS_BVNO_LEN));
            if (BPCSNOCK.END_NO == null) BPCSNOCK.END_NO = "";
            JIBS_tmp_int = BPCSNOCK.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.END_NO += " ";
            if (BPCSNOCK.END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_END_NO = 0;
            else WS_END_NO = Long.parseLong(BPCSNOCK.END_NO.substring(0, WS_BVNO_LEN));
            WS_TOTAL_NUM = (int) (WS_END_NO - WS_BEG_NO + 1);
            WS_OUT_END = BPCSNOCK.END_NO;
        } else {
            if (BPCSNOCK.BEG_NO == null) BPCSNOCK.BEG_NO = "";
            JIBS_tmp_int = BPCSNOCK.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BEG_NO += " ";
            if (BPCSNOCK.BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_BEG_NO = 0;
            else WS_BEG_NO = Long.parseLong(BPCSNOCK.BEG_NO.substring(0, WS_BVNO_LEN));
            WS_END_NO = WS_BEG_NO + BPCSNOCK.NUM - 1;
            WS_POS = 20;
            CEP.TRC(SCCGWA, "NCB022306");
            CEP.TRC(SCCGWA, WS_POS);
            CEP.TRC(SCCGWA, WS_BVNO_LEN);
            WS_POS = WS_POS - WS_BVNO_LEN + 1;
            CEP.TRC(SCCGWA, WS_POS);
            JIBS_tmp_str[0] = "" + WS_END_NO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<20-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_OUT_END = JIBS_tmp_str[0].substring(WS_POS - 1, WS_POS + WS_BVNO_LEN - 1);
            WS_TOTAL_NUM = BPCSNOCK.NUM;
        }
    }
    public void B040_END_TO_NUM_Y() throws IOException,SQLException,Exception {
        if (BPCSNOCK.BEG_NO == null) BPCSNOCK.BEG_NO = "";
        JIBS_tmp_int = BPCSNOCK.BEG_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BEG_NO += " ";
        if (BPCSNOCK.BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_BEG_NO = 0;
        else WS_BEG_NO = Long.parseLong(BPCSNOCK.BEG_NO.substring(0, WS_BVNO_LEN));
        if (BPCSNOCK.END_NO == null) BPCSNOCK.END_NO = "";
        JIBS_tmp_int = BPCSNOCK.END_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.END_NO += " ";
        if (BPCSNOCK.END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_END_NO = 0;
        else WS_END_NO = Long.parseLong(BPCSNOCK.END_NO.substring(0, WS_BVNO_LEN));
        CEP.TRC(SCCGWA, WS_BVNO_LEN);
        CEP.TRC(SCCGWA, WS_BEG_NO);
        CEP.TRC(SCCGWA, WS_END_NO);
        WS_TOTAL_NUM = (int) (WS_END_NO - WS_BEG_NO + 1);
        WS_NOLEN = (short) (WS_BVNO_LEN + 1);
        CEP.TRC(SCCGWA, WS_NOLEN);
        WS_POS = 20;
        WS_POS = WS_POS - WS_BVNO_LEN + 1;
        CEP.TRC(SCCGWA, WS_POS);
        JIBS_tmp_str[0] = "" + WS_BEG_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<20-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_COMP_BEG = JIBS_tmp_str[0].substring(WS_POS - 1, WS_POS + WS_BVNO_LEN - 1);
        JIBS_tmp_str[0] = "" + WS_END_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<20-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_COMP_END = JIBS_tmp_str[0].substring(WS_POS - 1, WS_POS + WS_BVNO_LEN - 1);
        for (WS_I = 1; WS_BEG_NO <= WS_END_NO; WS_I += 1) {
            JIBS_tmp_str[0] = "" + WS_END_NO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<20-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_COMP_END = JIBS_tmp_str[0].substring(WS_POS - 1, WS_POS + WS_BVNO_LEN - 1);
            if (WS_COMP_BEG == null) WS_COMP_BEG = "";
            JIBS_tmp_int = WS_COMP_BEG.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_COMP_BEG += " ";
            if (WS_COMP_BEG == null) WS_COMP_BEG = "";
            JIBS_tmp_int = WS_COMP_BEG.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_COMP_BEG += " ";
            for (WS_J = WS_STR_POS; WS_CHKNO != BPCFBVQU.TX_DATA.CHK_FLG 
                && WS_COMP_BEG.substring(WS_J - 1, WS_J + 1 - !1).equalsIgnoreCase(BPCFBVQU.TX_DATA.CHK_FLG+"") 
                && IBS.isNumeric(WS_COMP_BEG.substring(WS_J - 1, WS_J + 1 - 1)); WS_J += 1) {
            }
            if (BPCFBVQU.TX_DATA.BV_CFLG == 'Y') {
                IBS.init(SCCGWA, DCCFCDGG);
                CEP.TRC(SCCGWA, WS_COMP_BEG);
                DCCFCDGG.VAL.CARD_NO = WS_COMP_BEG;
                DCCFCDGG.VAL.FUNC = 'G';
                S000_CALL_DCZFCDGG();
                if (DCCFCDGG.VAL.CARD_NO_GEN == null) DCCFCDGG.VAL.CARD_NO_GEN = "";
                JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO_GEN.length();
                for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO_GEN += " ";
                WS_CHKNO = DCCFCDGG.VAL.CARD_NO_GEN.substring(WS_NOLEN - 1, WS_NOLEN + 1 - 1).charAt(0);
            }
            CEP.TRC(SCCGWA, WS_CHKNO);
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.CHK_FLG);
            if (WS_COMP_BEG == null) WS_COMP_BEG = "";
            JIBS_tmp_int = WS_COMP_BEG.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_COMP_BEG += " ";
            if (WS_CHKNO == BPCFBVQU.TX_DATA.CHK_FLG 
                || WS_COMP_BEG.substring(WS_J - 1, WS_J + 1 - 1).equalsIgnoreCase(BPCFBVQU.TX_DATA.CHK_FLG+"")) {
                WS_CNT = WS_CNT + 1;
            }
            WS_CHKNO = ' ';
            if (WS_COMP_BEG == null) WS_COMP_BEG = "";
            JIBS_tmp_int = WS_COMP_BEG.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_COMP_BEG += " ";
            if (WS_COMP_BEG.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_BEG_NO = 0;
            else WS_BEG_NO = Long.parseLong(WS_COMP_BEG.substring(0, WS_BVNO_LEN));
            CEP.TRC(SCCGWA, WS_BEG_NO);
            WS_BEG_NO += 1;
            CEP.TRC(SCCGWA, WS_BEG_NO);
            CEP.TRC(SCCGWA, WS_END_NO);
            JIBS_tmp_str[0] = "" + WS_BEG_NO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<20-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_COMP_BEG = JIBS_tmp_str[0].substring(WS_POS - 1, WS_POS + WS_BVNO_LEN - 1);
            CEP.TRC(SCCGWA, WS_COMP_BEG);
        }
        CEP.TRC(SCCGWA, "NCB032105");
        CEP.TRC(SCCGWA, WS_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_CNT);
        WS_TOTAL_NUM = WS_TOTAL_NUM - WS_CNT;
        WS_OUT_END = BPCSNOCK.END_NO;
    }
    public void B050_NUM_TO_END_Y() throws IOException,SQLException,Exception {
        if (BPCSNOCK.BEG_NO == null) BPCSNOCK.BEG_NO = "";
        JIBS_tmp_int = BPCSNOCK.BEG_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BEG_NO += " ";
        if (BPCSNOCK.BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_BEG_NO = 0;
        else WS_BEG_NO = Long.parseLong(BPCSNOCK.BEG_NO.substring(0, WS_BVNO_LEN));
        if (BPCSNOCK.BEG_NO == null) BPCSNOCK.BEG_NO = "";
        JIBS_tmp_int = BPCSNOCK.BEG_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BEG_NO += " ";
        if (BPCSNOCK.BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_END_NO = 0;
        else WS_END_NO = Long.parseLong(BPCSNOCK.BEG_NO.substring(0, WS_BVNO_LEN));
        WS_TOTAL_NUM = BPCSNOCK.NUM;
        CEP.TRC(SCCGWA, WS_BVNO_LEN);
        CEP.TRC(SCCGWA, WS_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_BEG_NO);
        CEP.TRC(SCCGWA, WS_END_NO);
        WS_NOLEN = (short) (WS_BVNO_LEN + 1);
        CEP.TRC(SCCGWA, WS_NOLEN);
        WS_POS = 20;
        CEP.TRC(SCCGWA, WS_POS);
        CEP.TRC(SCCGWA, WS_BVNO_LEN);
        CEP.TRC(SCCGWA, WS_BEG_NO);
        CEP.TRC(SCCGWA, WS_END_NO);
        WS_POS = WS_POS - WS_BVNO_LEN + 1;
        CEP.TRC(SCCGWA, WS_POS);
        if (BPCSNOCK.BEG_NO == null) BPCSNOCK.BEG_NO = "";
        JIBS_tmp_int = BPCSNOCK.BEG_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BEG_NO += " ";
        WS_COMP_BEG = BPCSNOCK.BEG_NO.substring(0, WS_BVNO_LEN);
        if (BPCSNOCK.BEG_NO == null) BPCSNOCK.BEG_NO = "";
        JIBS_tmp_int = BPCSNOCK.BEG_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BEG_NO += " ";
        WS_COMP_END = BPCSNOCK.BEG_NO.substring(0, WS_BVNO_LEN);
        CEP.TRC(SCCGWA, "NCB032110");
        CEP.TRC(SCCGWA, WS_COMP_BEG);
        CEP.TRC(SCCGWA, WS_COMP_END);
        for (WS_I = 1; WS_CNT != WS_TOTAL_NUM; WS_I += 1) {
            if (WS_COMP_END == null) WS_COMP_END = "";
            JIBS_tmp_int = WS_COMP_END.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_COMP_END += " ";
            if (WS_COMP_END == null) WS_COMP_END = "";
            JIBS_tmp_int = WS_COMP_END.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_COMP_END += " ";
            for (WS_J = WS_STR_POS; WS_CHKNO != BPCFBVQU.TX_DATA.CHK_FLG 
                && WS_COMP_END.substring(WS_J - 1, WS_J + 1 - !1).equalsIgnoreCase(BPCFBVQU.TX_DATA.CHK_FLG+"") 
                && IBS.isNumeric(WS_COMP_END.substring(WS_J - 1, WS_J + 1 - 1)); WS_J += 1) {
            }
            if (BPCFBVQU.TX_DATA.BV_CFLG == 'Y') {
                IBS.init(SCCGWA, DCCFCDGG);
                CEP.TRC(SCCGWA, WS_COMP_END);
                DCCFCDGG.VAL.CARD_NO = WS_COMP_END;
                CEP.TRC(SCCGWA, "NCB032107");
                CEP.TRC(SCCGWA, WS_CNT);
                CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
                DCCFCDGG.VAL.FUNC = 'G';
                S000_CALL_DCZFCDGG();
                CEP.TRC(SCCGWA, "NCB032108");
                CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO_GEN);
                if (DCCFCDGG.VAL.CARD_NO_GEN == null) DCCFCDGG.VAL.CARD_NO_GEN = "";
                JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO_GEN.length();
                for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO_GEN += " ";
                WS_CHKNO = DCCFCDGG.VAL.CARD_NO_GEN.substring(WS_NOLEN - 1, WS_NOLEN + 1 - 1).charAt(0);
            }
            CEP.TRC(SCCGWA, WS_CHKNO);
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.CHK_FLG);
            CEP.TRC(SCCGWA, WS_J);
            if (WS_COMP_END == null) WS_COMP_END = "";
            JIBS_tmp_int = WS_COMP_END.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_COMP_END += " ";
            CEP.TRC(SCCGWA, WS_COMP_END.substring(WS_J - 1, WS_J + 1 - 1));
            if (WS_COMP_END == null) WS_COMP_END = "";
            JIBS_tmp_int = WS_COMP_END.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_COMP_END += " ";
            if (WS_CHKNO != BPCFBVQU.TX_DATA.CHK_FLG 
                && !WS_COMP_END.substring(WS_J - 1, WS_J + 1 - 1).equalsIgnoreCase(BPCFBVQU.TX_DATA.CHK_FLG+"")) {
                WS_CNT = WS_CNT + 1;
            }
            WS_CHKNO = ' ';
            if (WS_COMP_END == null) WS_COMP_END = "";
            JIBS_tmp_int = WS_COMP_END.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_COMP_END += " ";
            if (WS_COMP_END.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_END_NO = 0;
            else WS_END_NO = Long.parseLong(WS_COMP_END.substring(0, WS_BVNO_LEN));
            CEP.TRC(SCCGWA, WS_END_NO);
            if (WS_CNT != WS_TOTAL_NUM) {
                WS_END_NO += 1;
            }
            JIBS_tmp_str[0] = "" + WS_END_NO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<20-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_COMP_END = JIBS_tmp_str[0].substring(WS_POS - 1, WS_POS + WS_BVNO_LEN - 1);
            CEP.TRC(SCCGWA, WS_COMP_END);
        }
        WS_OUT_END = WS_COMP_END;
    }
    public void B060_CHECK_BV_NUM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSNOCK.END_NO);
        CEP.TRC(SCCGWA, WS_OUT_END);
        CEP.TRC(SCCGWA, WS_TOTAL_NUM);
        CEP.TRC(SCCGWA, BPCSNOCK.NUM);
        if (BPCSNOCK.END_NO.trim().length() > 0 
            && BPCSNOCK.NUM != 0 
            && BPCSNOCK.BEG_NO.trim().length() > 0 
            && (!BPCSNOCK.END_NO.equalsIgnoreCase(WS_OUT_END) 
            || WS_TOTAL_NUM != BPCSNOCK.NUM)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END_NUM;
            if (BPCSNOCK.END_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BPCSNOCK.END_NO);
            S000_ERR_MSG_PROC();
        } else {
            CEP.TRC(SCCGWA, "NCB032307");
        }
    }
    public void B030_FND_NEXT_PRE() throws IOException,SQLException,Exception {
        if (BPCFBVQU.TX_DATA.CHK_FLG == ' ') {
            B040_FIND_NEXT_AND_PRE();
        } else {
            if (BPCSNOCK.FUNC == '4') {
                B050_FND_NEXT_NO();
            } else {
                B060_FND_PRE_NO();
            }
        }
    }
    public void B040_FIND_NEXT_AND_PRE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NCB032402");
        CEP.TRC(SCCGWA, BPCSNOCK.BV_NO);
        if (BPCSNOCK.BV_NO == null) BPCSNOCK.BV_NO = "";
        JIBS_tmp_int = BPCSNOCK.BV_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BV_NO += " ";
        if (BPCSNOCK.BV_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_BV_NO = 0;
        else WS_BV_NO = Long.parseLong(BPCSNOCK.BV_NO.substring(0, WS_BVNO_LEN));
        WS_NEXT_NO = WS_BV_NO + 1;
        WS_PRE_NO = WS_BV_NO - 1;
        if (WS_PRE_NO == 0) {
            WS_PRE_NO = WS_BV_NO + 1;
        }
        WS_NOLEN = (short) (WS_BVNO_LEN + 1);
        WS_POS = 20;
        WS_POS = WS_POS - WS_BVNO_LEN + 1;
        CEP.TRC(SCCGWA, WS_POS);
        JIBS_tmp_str[0] = "" + WS_NEXT_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<20-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSNOCK.NEXT_NO = JIBS_tmp_str[0].substring(WS_POS - 1, WS_POS + WS_BVNO_LEN - 1);
        JIBS_tmp_str[0] = "" + WS_PRE_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<20-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSNOCK.PRE_NO = JIBS_tmp_str[0].substring(WS_POS - 1, WS_POS + WS_BVNO_LEN - 1);
        CEP.TRC(SCCGWA, BPCSNOCK.NEXT_NO);
        CEP.TRC(SCCGWA, BPCSNOCK.PRE_NO);
    }
    public void B050_FND_NEXT_NO() throws IOException,SQLException,Exception {
        if (BPCSNOCK.BV_NO == null) BPCSNOCK.BV_NO = "";
        JIBS_tmp_int = BPCSNOCK.BV_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BV_NO += " ";
        if (BPCSNOCK.BV_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_BEG_NO = 0;
        else WS_BEG_NO = Long.parseLong(BPCSNOCK.BV_NO.substring(0, WS_BVNO_LEN));
        if (BPCSNOCK.BV_NO == null) BPCSNOCK.BV_NO = "";
        JIBS_tmp_int = BPCSNOCK.BV_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BV_NO += " ";
        if (BPCSNOCK.BV_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_END_NO = 0;
        else WS_END_NO = Long.parseLong(BPCSNOCK.BV_NO.substring(0, WS_BVNO_LEN));
        WS_TOTAL_NUM = 2;
        CEP.TRC(SCCGWA, WS_TOTAL_NUM);
        CEP.TRC(SCCGWA, "NCB032403");
        CEP.TRC(SCCGWA, WS_BVNO_LEN);
        WS_NOLEN = (short) (WS_BVNO_LEN + 1);
        CEP.TRC(SCCGWA, WS_NOLEN);
        WS_POS = 20;
        CEP.TRC(SCCGWA, WS_POS);
        CEP.TRC(SCCGWA, WS_BVNO_LEN);
        CEP.TRC(SCCGWA, WS_BEG_NO);
        CEP.TRC(SCCGWA, WS_END_NO);
        WS_POS = WS_POS - WS_BVNO_LEN + 1;
        CEP.TRC(SCCGWA, WS_POS);
        if (BPCSNOCK.BV_NO == null) BPCSNOCK.BV_NO = "";
        JIBS_tmp_int = BPCSNOCK.BV_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BV_NO += " ";
        WS_COMP_BEG = BPCSNOCK.BV_NO.substring(0, WS_BVNO_LEN);
        if (BPCSNOCK.BV_NO == null) BPCSNOCK.BV_NO = "";
        JIBS_tmp_int = BPCSNOCK.BV_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BV_NO += " ";
        WS_COMP_END = BPCSNOCK.BV_NO.substring(0, WS_BVNO_LEN);
        CEP.TRC(SCCGWA, "NCB032404");
        CEP.TRC(SCCGWA, WS_COMP_BEG);
        CEP.TRC(SCCGWA, WS_COMP_END);
        for (WS_I = 1; WS_CNT != WS_TOTAL_NUM; WS_I += 1) {
            if (WS_COMP_END == null) WS_COMP_END = "";
            JIBS_tmp_int = WS_COMP_END.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_COMP_END += " ";
            if (WS_COMP_END == null) WS_COMP_END = "";
            JIBS_tmp_int = WS_COMP_END.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_COMP_END += " ";
            for (WS_J = WS_STR_POS; WS_CHKNO != BPCFBVQU.TX_DATA.CHK_FLG 
                && WS_COMP_END.substring(WS_J - 1, WS_J + 1 - !1).equalsIgnoreCase(BPCFBVQU.TX_DATA.CHK_FLG+"") 
                && IBS.isNumeric(WS_COMP_END.substring(WS_J - 1, WS_J + 1 - 1)); WS_J += 1) {
            }
            if (BPCFBVQU.TX_DATA.BV_CFLG == 'Y') {
                IBS.init(SCCGWA, DCCFCDGG);
                CEP.TRC(SCCGWA, WS_COMP_END);
                DCCFCDGG.VAL.CARD_NO = WS_COMP_END;
                CEP.TRC(SCCGWA, "NCB032405");
                CEP.TRC(SCCGWA, WS_CNT);
                CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
                DCCFCDGG.VAL.FUNC = 'G';
                S000_CALL_DCZFCDGG();
                CEP.TRC(SCCGWA, "NCB032406");
                CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO_GEN);
                if (DCCFCDGG.VAL.CARD_NO_GEN == null) DCCFCDGG.VAL.CARD_NO_GEN = "";
                JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO_GEN.length();
                for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO_GEN += " ";
                WS_CHKNO = DCCFCDGG.VAL.CARD_NO_GEN.substring(WS_NOLEN - 1, WS_NOLEN + 1 - 1).charAt(0);
            }
            CEP.TRC(SCCGWA, WS_CHKNO);
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.CHK_FLG);
            CEP.TRC(SCCGWA, WS_J);
            if (WS_COMP_END == null) WS_COMP_END = "";
            JIBS_tmp_int = WS_COMP_END.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_COMP_END += " ";
            CEP.TRC(SCCGWA, WS_COMP_END.substring(WS_J - 1, WS_J + 1 - 1));
            if (WS_COMP_END == null) WS_COMP_END = "";
            JIBS_tmp_int = WS_COMP_END.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_COMP_END += " ";
            if (WS_CHKNO != BPCFBVQU.TX_DATA.CHK_FLG 
                && !WS_COMP_END.substring(WS_J - 1, WS_J + 1 - 1).equalsIgnoreCase(BPCFBVQU.TX_DATA.CHK_FLG+"")) {
                WS_CNT = WS_CNT + 1;
            }
            WS_CHKNO = ' ';
            if (WS_COMP_END == null) WS_COMP_END = "";
            JIBS_tmp_int = WS_COMP_END.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_COMP_END += " ";
            if (WS_COMP_END.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_END_NO = 0;
            else WS_END_NO = Long.parseLong(WS_COMP_END.substring(0, WS_BVNO_LEN));
            CEP.TRC(SCCGWA, WS_END_NO);
            if (WS_CNT != WS_TOTAL_NUM) {
                WS_END_NO += 1;
            }
            CEP.TRC(SCCGWA, "NCB032409");
            CEP.TRC(SCCGWA, WS_BEG_NO);
            CEP.TRC(SCCGWA, WS_END_NO);
            JIBS_tmp_str[0] = "" + WS_END_NO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<20-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_COMP_END = JIBS_tmp_str[0].substring(WS_POS - 1, WS_POS + WS_BVNO_LEN - 1);
            CEP.TRC(SCCGWA, WS_COMP_END);
        }
        BPCSNOCK.NEXT_NO = WS_COMP_END;
    }
    public void B060_FND_PRE_NO() throws IOException,SQLException,Exception {
        if (BPCSNOCK.BV_NO == null) BPCSNOCK.BV_NO = "";
        JIBS_tmp_int = BPCSNOCK.BV_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BV_NO += " ";
        if (BPCSNOCK.BV_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_BEG_NO = 0;
        else WS_BEG_NO = Long.parseLong(BPCSNOCK.BV_NO.substring(0, WS_BVNO_LEN));
        if (BPCSNOCK.BV_NO == null) BPCSNOCK.BV_NO = "";
        JIBS_tmp_int = BPCSNOCK.BV_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BV_NO += " ";
        if (BPCSNOCK.BV_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_END_NO = 0;
        else WS_END_NO = Long.parseLong(BPCSNOCK.BV_NO.substring(0, WS_BVNO_LEN));
        WS_TOTAL_NUM = 2;
        WS_NOLEN = (short) (WS_BVNO_LEN + 1);
        CEP.TRC(SCCGWA, WS_NOLEN);
        WS_POS = 20;
        CEP.TRC(SCCGWA, WS_POS);
        CEP.TRC(SCCGWA, WS_BVNO_LEN);
        CEP.TRC(SCCGWA, WS_BEG_NO);
        CEP.TRC(SCCGWA, WS_END_NO);
        WS_POS = WS_POS - WS_BVNO_LEN + 1;
        CEP.TRC(SCCGWA, WS_POS);
        if (BPCSNOCK.BV_NO == null) BPCSNOCK.BV_NO = "";
        JIBS_tmp_int = BPCSNOCK.BV_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BV_NO += " ";
        WS_COMP_BEG = BPCSNOCK.BV_NO.substring(0, WS_BVNO_LEN);
        if (BPCSNOCK.BV_NO == null) BPCSNOCK.BV_NO = "";
        JIBS_tmp_int = BPCSNOCK.BV_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSNOCK.BV_NO += " ";
        WS_COMP_END = BPCSNOCK.BV_NO.substring(0, WS_BVNO_LEN);
        CEP.TRC(SCCGWA, "NCB032110");
        CEP.TRC(SCCGWA, WS_COMP_BEG);
        CEP.TRC(SCCGWA, WS_COMP_END);
        for (WS_I = 1; WS_CNT != WS_TOTAL_NUM; WS_I += 1) {
            if (WS_COMP_END == null) WS_COMP_END = "";
            JIBS_tmp_int = WS_COMP_END.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_COMP_END += " ";
            if (WS_COMP_END == null) WS_COMP_END = "";
            JIBS_tmp_int = WS_COMP_END.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_COMP_END += " ";
            for (WS_J = WS_STR_POS; WS_CHKNO != BPCFBVQU.TX_DATA.CHK_FLG 
                && WS_COMP_END.substring(WS_J - 1, WS_J + 1 - !1).equalsIgnoreCase(BPCFBVQU.TX_DATA.CHK_FLG+"") 
                && IBS.isNumeric(WS_COMP_END.substring(WS_J - 1, WS_J + 1 - 1)); WS_J += 1) {
            }
            if (BPCFBVQU.TX_DATA.BV_CFLG == 'Y') {
                IBS.init(SCCGWA, DCCFCDGG);
                CEP.TRC(SCCGWA, WS_COMP_END);
                DCCFCDGG.VAL.CARD_NO = WS_COMP_END;
                CEP.TRC(SCCGWA, "NCB032107");
                CEP.TRC(SCCGWA, WS_CNT);
                CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
                DCCFCDGG.VAL.FUNC = 'G';
                S000_CALL_DCZFCDGG();
                CEP.TRC(SCCGWA, "NCB032108");
                CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO_GEN);
                if (DCCFCDGG.VAL.CARD_NO_GEN == null) DCCFCDGG.VAL.CARD_NO_GEN = "";
                JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO_GEN.length();
                for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO_GEN += " ";
                WS_CHKNO = DCCFCDGG.VAL.CARD_NO_GEN.substring(WS_NOLEN - 1, WS_NOLEN + 1 - 1).charAt(0);
            }
            CEP.TRC(SCCGWA, WS_CHKNO);
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.CHK_FLG);
            CEP.TRC(SCCGWA, WS_J);
            if (WS_COMP_END == null) WS_COMP_END = "";
            JIBS_tmp_int = WS_COMP_END.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_COMP_END += " ";
            CEP.TRC(SCCGWA, WS_COMP_END.substring(WS_J - 1, WS_J + 1 - 1));
            if (WS_COMP_END == null) WS_COMP_END = "";
            JIBS_tmp_int = WS_COMP_END.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_COMP_END += " ";
            if (WS_CHKNO != BPCFBVQU.TX_DATA.CHK_FLG 
                && !WS_COMP_END.substring(WS_J - 1, WS_J + 1 - 1).equalsIgnoreCase(BPCFBVQU.TX_DATA.CHK_FLG+"")) {
                WS_CNT = WS_CNT + 1;
            }
            WS_CHKNO = ' ';
            if (WS_COMP_END == null) WS_COMP_END = "";
            JIBS_tmp_int = WS_COMP_END.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_COMP_END += " ";
            if (WS_COMP_END.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_END_NO = 0;
            else WS_END_NO = Long.parseLong(WS_COMP_END.substring(0, WS_BVNO_LEN));
            CEP.TRC(SCCGWA, WS_END_NO);
            if (WS_CNT != WS_TOTAL_NUM) {
                WS_END_NO -= 1;
            }
            CEP.TRC(SCCGWA, "NCB032111");
            CEP.TRC(SCCGWA, WS_BEG_NO);
            CEP.TRC(SCCGWA, WS_END_NO);
            JIBS_tmp_str[0] = "" + WS_END_NO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<20-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_COMP_END = JIBS_tmp_str[0].substring(WS_POS - 1, WS_POS + WS_BVNO_LEN - 1);
            CEP.TRC(SCCGWA, WS_COMP_END);
        }
        BPCSNOCK.PRE_NO = WS_COMP_END;
    }
    public void B070_DATA_OUTPUT() throws IOException,SQLException,Exception {
        BPCSNOCK.END_NO = WS_OUT_END;
        BPCSNOCK.NUM = WS_TOTAL_NUM;
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZFCDGG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZFCDGG, DCCFCDGG);
        CEP.TRC(SCCGWA, DCCFCDGG.RC);
        if (DCCFCDGG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "1");
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCFCDGG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
