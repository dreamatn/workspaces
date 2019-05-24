package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5290 {
    DBParm TDTSMST_RD;
    brParm TDTSMST_BR = new brParm();
    DBParm TDTINST_RD;
    DBParm TDTOTHE_RD;
    DBParm TDTBVT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int WS_BEG_DT = 0;
    int WS_END_DT = 0;
    String K_OUTPUT_FMT = "TD529";
    int K_SCR_ROW_NO = 10;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    String WS_MSGID = " ";
    short WS_TIME = 0;
    TDOT5290_WS_DATA WS_DATA = new TDOT5290_WS_DATA();
    char WS_TBL_FLAG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    CICQACAC CICQACAC = new CICQACAC();
    CICQCIAC CICQCIAC = new CICQCIAC();
    TDRINST TDRINST = new TDRINST();
    TDRSMST TDRSMST = new TDRSMST();
    TDROTHE TDROTHE = new TDROTHE();
    TDRBVT TDRBVT = new TDRBVT();
    SCCGWA SCCGWA;
    TDB5290_AWA_5290 TDB5290_AWA_5290;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDOT5290 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5290_AWA_5290>");
        TDB5290_AWA_5290 = (TDB5290_AWA_5290) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        R000_PAGE_ROW();
        if (pgmRtn) return;
        if (TDB5290_AWA_5290.FUNC == '1') {
            B020_QUERY_BY_CI_NO();
            if (pgmRtn) return;
        } else if (TDB5290_AWA_5290.FUNC == '2') {
            B030_QUERY_BY_ACTI_NO();
            if (pgmRtn) return;
        } else if (TDB5290_AWA_5290.FUNC == '3') {
            if (TDB5290_AWA_5290.DD_AC.trim().length() > 0) {
                B040_QUERY_BY_DDAC();
                if (pgmRtn) return;
            } else {
                B041_QUERY_BY_TDAC();
                if (pgmRtn) return;
            }
        } else if (TDB5290_AWA_5290.FUNC == '4') {
            B050_QUERY_BY_OCAC();
            if (pgmRtn) return;
        } else {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB5290_AWA_5290.FUNC);
        if (TDB5290_AWA_5290.FUNC == '1') {
            if (TDB5290_AWA_5290.CI_NO.trim().length() == 0) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_CI_NO_MST_IPT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else if (TDB5290_AWA_5290.FUNC == '2') {
            if (TDB5290_AWA_5290.ACTI_NO.trim().length() == 0) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_ACTI_NO_IN_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else if (TDB5290_AWA_5290.FUNC == '3') {
            if (TDB5290_AWA_5290.DD_AC.trim().length() == 0 
                && TDB5290_AWA_5290.TD_AC.trim().length() == 0) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else if (TDB5290_AWA_5290.FUNC == '4') {
            if (TDB5290_AWA_5290.BEG_DT == 0 
                || TDB5290_AWA_5290.END_DT == 0) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_DT_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (TDB5290_AWA_5290.BEG_DT > TDB5290_AWA_5290.END_DT) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_SDT_GT_DDT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_BY_CI_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQCIAC);
        CICQCIAC.FUNC = '2';
        CICQCIAC.DATA.CI_NO = TDB5290_AWA_5290.CI_NO;
        CICQCIAC.DATA.PROD_CD = "2206070101";
        CICQCIAC.DATA.FRM_APP = "TD";
        while (CICQCIAC.DATA.LAST_FLG != 'Y') {
            S000_CALL_CIZQCIAC();
            if (pgmRtn) return;
            WS_TIME = 0;
            WS_TIME = 1;
            while (WS_TIME <= 100 
                && CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TIME-1].ENTY_NO.trim().length() != 0) {
                IBS.init(SCCGWA, WS_DATA);
                IBS.init(SCCGWA, CICQACAC);
                CICQACAC.FUNC = 'A';
                CICQACAC.DATA.ACAC_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TIME-1].ENTY_NO;
                S000_CALL_CIZQACAC();
                if (pgmRtn) return;
                WS_DATA.WS_TD_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
                WS_DATA.WS_AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
                WS_DATA.WS_AC_NM = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.KEY.ACO_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TIME-1].ENTY_NO;
                T000_READ_TDTSMST();
                if (pgmRtn) return;
                WS_DATA.WS_STS = TDRSMST.ACO_STS;
                WS_DATA.WS_CCY = TDRSMST.CCY;
                WS_DATA.WS_CHNL_NO = TDRSMST.CHNL_NO;
                WS_DATA.WS_ACTI_NO = TDRSMST.ACTI_NO;
                WS_DATA.WS_TERM = TDRSMST.TERM;
                WS_DATA.WS_OPEN_DT = TDRSMST.OPEN_DATE;
                WS_DATA.WS_OPEN_BR = TDRSMST.OWNER_BR;
                WS_DATA.WS_OPEN_AC = TDRSMST.OPEN_DR_AC;
                WS_DATA.WS_CLOSE_DT = TDRSMST.CLO_DATE;
                WS_DATA.WS_BV_TYP = TDRSMST.BV_TYP;
                WS_DATA.WS_VAL_DT = TDRSMST.VAL_DATE;
                WS_DATA.WS_EXP_DT = TDRSMST.EXP_DATE;
                WS_DATA.WS_BAL = TDRSMST.BAL;
                IBS.init(SCCGWA, TDROTHE);
                TDROTHE.KEY.ACTI_NO = TDRSMST.ACTI_NO;
                T000_READ_TDTOTHE();
                if (pgmRtn) return;
                WS_DATA.WS_ACTI_NM = TDROTHE.ACTI_DESC;
                WS_DATA.WS_LOW_RAT = TDROTHE.MIN_RAT;
                WS_DATA.WS_HIGH_RAT = TDROTHE.MAX_RAT;
                WS_DATA.WS_CLOSE_RAT = TDROTHE.CONT_RAT;
                IBS.init(SCCGWA, TDRBVT);
                TDRBVT.KEY.AC_NO = TDRSMST.KEY.ACO_AC;
                T000_READ_TDTBVT();
                if (pgmRtn) return;
                WS_DATA.WS_BV_NO = TDRBVT.BV_NO;
                IBS.init(SCCGWA, TDRINST);
                TDRINST.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
                T000_READ_TDTINST();
                if (pgmRtn) return;
                WS_DATA.WS_EXP_AC = TDRINST.STL_AC;
                B080_OUT_LIST();
                if (pgmRtn) return;
                WS_TIME += 1;
            }
        }
    }
    public void B030_QUERY_BY_ACTI_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.ACTI_NO = TDB5290_AWA_5290.ACTI_NO;
        T000_STARTBR_TDTSMST_BY_ACTI_NO();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        while (WS_TBL_FLAG != 'N') {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'A';
            CICQACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            WS_DATA.WS_TD_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            WS_DATA.WS_AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
            WS_DATA.WS_AC_NM = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
            WS_DATA.WS_STS = TDRSMST.ACO_STS;
            WS_DATA.WS_CCY = TDRSMST.CCY;
            WS_DATA.WS_CHNL_NO = TDRSMST.CHNL_NO;
            WS_DATA.WS_ACTI_NO = TDRSMST.ACTI_NO;
            WS_DATA.WS_TERM = TDRSMST.TERM;
            WS_DATA.WS_OPEN_DT = TDRSMST.OPEN_DATE;
            WS_DATA.WS_OPEN_BR = TDRSMST.OWNER_BR;
            WS_DATA.WS_OPEN_AC = TDRSMST.OPEN_DR_AC;
            WS_DATA.WS_CLOSE_DT = TDRSMST.CLO_DATE;
            WS_DATA.WS_BV_TYP = TDRSMST.BV_TYP;
            WS_DATA.WS_VAL_DT = TDRSMST.VAL_DATE;
            WS_DATA.WS_EXP_DT = TDRSMST.EXP_DATE;
            WS_DATA.WS_BAL = TDRSMST.BAL;
            IBS.init(SCCGWA, TDROTHE);
            TDROTHE.KEY.ACTI_NO = TDRSMST.ACTI_NO;
            T000_READ_TDTOTHE();
            if (pgmRtn) return;
            WS_DATA.WS_ACTI_NM = TDROTHE.ACTI_DESC;
            WS_DATA.WS_LOW_RAT = TDROTHE.MIN_RAT;
            WS_DATA.WS_HIGH_RAT = TDROTHE.MAX_RAT;
            WS_DATA.WS_CLOSE_RAT = TDROTHE.CONT_RAT;
            IBS.init(SCCGWA, TDRBVT);
            TDRBVT.KEY.AC_NO = TDRSMST.KEY.ACO_AC;
            T000_READ_TDTBVT();
            if (pgmRtn) return;
            WS_DATA.WS_BV_NO = TDRBVT.BV_NO;
            IBS.init(SCCGWA, TDRINST);
            TDRINST.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            T000_READ_TDTINST();
            if (pgmRtn) return;
            WS_DATA.WS_EXP_AC = TDRINST.STL_AC;
            B080_OUT_LIST();
            if (pgmRtn) return;
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
        }
    }
    public void B040_QUERY_BY_DDAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.OPEN_DR_AC = TDB5290_AWA_5290.DD_AC;
        T000_STARTBR_TDTSMST_BY_DD_AC();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        while (WS_TBL_FLAG != 'N') {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'A';
            CICQACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            WS_DATA.WS_TD_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            WS_DATA.WS_AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
            WS_DATA.WS_AC_NM = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
            WS_DATA.WS_STS = TDRSMST.ACO_STS;
            WS_DATA.WS_CCY = TDRSMST.CCY;
            WS_DATA.WS_CHNL_NO = TDRSMST.CHNL_NO;
            WS_DATA.WS_ACTI_NO = TDRSMST.ACTI_NO;
            WS_DATA.WS_TERM = TDRSMST.TERM;
            WS_DATA.WS_OPEN_DT = TDRSMST.OPEN_DATE;
            WS_DATA.WS_OPEN_BR = TDRSMST.OWNER_BR;
            WS_DATA.WS_OPEN_AC = TDRSMST.OPEN_DR_AC;
            WS_DATA.WS_CLOSE_DT = TDRSMST.CLO_DATE;
            WS_DATA.WS_BV_TYP = TDRSMST.BV_TYP;
            WS_DATA.WS_VAL_DT = TDRSMST.VAL_DATE;
            WS_DATA.WS_EXP_DT = TDRSMST.EXP_DATE;
            WS_DATA.WS_BAL = TDRSMST.BAL;
            IBS.init(SCCGWA, TDROTHE);
            TDROTHE.KEY.ACTI_NO = TDRSMST.ACTI_NO;
            T000_READ_TDTOTHE();
            if (pgmRtn) return;
            WS_DATA.WS_ACTI_NM = TDROTHE.ACTI_DESC;
            WS_DATA.WS_LOW_RAT = TDROTHE.MIN_RAT;
            WS_DATA.WS_HIGH_RAT = TDROTHE.MAX_RAT;
            WS_DATA.WS_CLOSE_RAT = TDROTHE.CONT_RAT;
            IBS.init(SCCGWA, TDRBVT);
            TDRBVT.KEY.AC_NO = TDRSMST.KEY.ACO_AC;
            T000_READ_TDTBVT();
            if (pgmRtn) return;
            WS_DATA.WS_BV_NO = TDRBVT.BV_NO;
            IBS.init(SCCGWA, TDRINST);
            TDRINST.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            T000_READ_TDTINST();
            if (pgmRtn) return;
            WS_DATA.WS_EXP_AC = TDRINST.STL_AC;
            B080_OUT_LIST();
            if (pgmRtn) return;
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
        }
    }
    public void B041_QUERY_BY_TDAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDB5290_AWA_5290.TD_AC;
        T000_STARTBR_TDTSMST_BY_TD_AC();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        while (WS_TBL_FLAG != 'N') {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'A';
            CICQACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            WS_DATA.WS_TD_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            WS_DATA.WS_AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
            WS_DATA.WS_AC_NM = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
            WS_DATA.WS_STS = TDRSMST.ACO_STS;
            WS_DATA.WS_CCY = TDRSMST.CCY;
            WS_DATA.WS_CHNL_NO = TDRSMST.CHNL_NO;
            WS_DATA.WS_ACTI_NO = TDRSMST.ACTI_NO;
            WS_DATA.WS_TERM = TDRSMST.TERM;
            WS_DATA.WS_OPEN_DT = TDRSMST.OPEN_DATE;
            WS_DATA.WS_OPEN_BR = TDRSMST.OWNER_BR;
            WS_DATA.WS_OPEN_AC = TDRSMST.OPEN_DR_AC;
            WS_DATA.WS_CLOSE_DT = TDRSMST.CLO_DATE;
            WS_DATA.WS_BV_TYP = TDRSMST.BV_TYP;
            WS_DATA.WS_VAL_DT = TDRSMST.VAL_DATE;
            WS_DATA.WS_EXP_DT = TDRSMST.EXP_DATE;
            WS_DATA.WS_BAL = TDRSMST.BAL;
            IBS.init(SCCGWA, TDROTHE);
            TDROTHE.KEY.ACTI_NO = TDRSMST.ACTI_NO;
            T000_READ_TDTOTHE();
            if (pgmRtn) return;
            WS_DATA.WS_ACTI_NM = TDROTHE.ACTI_DESC;
            WS_DATA.WS_LOW_RAT = TDROTHE.MIN_RAT;
            WS_DATA.WS_HIGH_RAT = TDROTHE.MAX_RAT;
            WS_DATA.WS_CLOSE_RAT = TDROTHE.CONT_RAT;
            IBS.init(SCCGWA, TDRBVT);
            TDRBVT.KEY.AC_NO = TDRSMST.KEY.ACO_AC;
            T000_READ_TDTBVT();
            if (pgmRtn) return;
            WS_DATA.WS_BV_NO = TDRBVT.BV_NO;
            IBS.init(SCCGWA, TDRINST);
            TDRINST.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            T000_READ_TDTINST();
            if (pgmRtn) return;
            WS_DATA.WS_EXP_AC = TDRINST.STL_AC;
            B080_OUT_LIST();
            if (pgmRtn) return;
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
        }
    }
    public void B050_QUERY_BY_OCAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        WS_BEG_DT = TDB5290_AWA_5290.BEG_DT;
        WS_END_DT = TDB5290_AWA_5290.END_DT;
        T000_STARTBR_TDTSMST_BY_OCAC();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        while (WS_TBL_FLAG != 'N') {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'A';
            CICQACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            WS_DATA.WS_TD_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            WS_DATA.WS_AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
            WS_DATA.WS_AC_NM = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
            WS_DATA.WS_STS = TDRSMST.ACO_STS;
            WS_DATA.WS_CCY = TDRSMST.CCY;
            WS_DATA.WS_CHNL_NO = TDRSMST.CHNL_NO;
            WS_DATA.WS_ACTI_NO = TDRSMST.ACTI_NO;
            WS_DATA.WS_TERM = TDRSMST.TERM;
            WS_DATA.WS_OPEN_DT = TDRSMST.OPEN_DATE;
            WS_DATA.WS_OPEN_BR = TDRSMST.OWNER_BR;
            WS_DATA.WS_OPEN_AC = TDRSMST.OPEN_DR_AC;
            WS_DATA.WS_CLOSE_DT = TDRSMST.CLO_DATE;
            WS_DATA.WS_BV_TYP = TDRSMST.BV_TYP;
            WS_DATA.WS_VAL_DT = TDRSMST.VAL_DATE;
            WS_DATA.WS_EXP_DT = TDRSMST.EXP_DATE;
            WS_DATA.WS_BAL = TDRSMST.BAL;
            IBS.init(SCCGWA, TDROTHE);
            TDROTHE.KEY.ACTI_NO = TDRSMST.ACTI_NO;
            T000_READ_TDTOTHE();
            if (pgmRtn) return;
            WS_DATA.WS_ACTI_NM = TDROTHE.ACTI_DESC;
            WS_DATA.WS_LOW_RAT = TDROTHE.MIN_RAT;
            WS_DATA.WS_HIGH_RAT = TDROTHE.MAX_RAT;
            WS_DATA.WS_CLOSE_RAT = TDROTHE.CONT_RAT;
            IBS.init(SCCGWA, TDRBVT);
            TDRBVT.KEY.AC_NO = TDRSMST.KEY.ACO_AC;
            T000_READ_TDTBVT();
            if (pgmRtn) return;
            WS_DATA.WS_BV_NO = TDRBVT.BV_NO;
            IBS.init(SCCGWA, TDRINST);
            TDRINST.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            T000_READ_TDTINST();
            if (pgmRtn) return;
            WS_DATA.WS_EXP_AC = TDRINST.STL_AC;
            B080_OUT_LIST();
            if (pgmRtn) return;
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
        }
    }
    public void B080_OUT_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_DATA.WS_TD_AC);
        CEP.TRC(SCCGWA, WS_DATA.WS_AC_SEQ);
        CEP.TRC(SCCGWA, WS_DATA.WS_AC_NM);
        CEP.TRC(SCCGWA, WS_DATA.WS_CCY);
        CEP.TRC(SCCGWA, WS_DATA.WS_STS);
        CEP.TRC(SCCGWA, WS_DATA.WS_CHNL_NO);
        CEP.TRC(SCCGWA, WS_DATA.WS_ACTI_NO);
        CEP.TRC(SCCGWA, WS_DATA.WS_ACTI_NM);
        CEP.TRC(SCCGWA, WS_DATA.WS_OPEN_DT);
        CEP.TRC(SCCGWA, WS_DATA.WS_TERM);
        CEP.TRC(SCCGWA, WS_DATA.WS_BAL);
        CEP.TRC(SCCGWA, WS_DATA.WS_LOW_RAT);
        CEP.TRC(SCCGWA, WS_DATA.WS_HIGH_RAT);
        CEP.TRC(SCCGWA, WS_DATA.WS_VAL_DT);
        CEP.TRC(SCCGWA, WS_DATA.WS_EXP_DT);
        CEP.TRC(SCCGWA, WS_DATA.WS_BV_TYP);
        CEP.TRC(SCCGWA, WS_DATA.WS_BV_NO);
        CEP.TRC(SCCGWA, WS_DATA.WS_OPEN_AC);
        CEP.TRC(SCCGWA, WS_DATA.WS_OPEN_BR);
        CEP.TRC(SCCGWA, WS_DATA.WS_CLOSE_DT);
        CEP.TRC(SCCGWA, WS_DATA.WS_CLOSE_RAT);
        CEP.TRC(SCCGWA, WS_DATA.WS_EXP_AC);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_DATA);
        SCCMPAG.DATA_LEN = 563;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
        if (CICQCIAC.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICQCIAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void R000_PAGE_ROW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_STARTBR_TDTSMST_BY_ACTI_NO() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "ACTI_NO = :TDRSMST.ACTI_NO "
            + "AND PROD_CD = '2206070101' "
            + "AND PRDAC_CD = '037'";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
            }
        }
    }
    public void T000_STARTBR_TDTSMST_BY_DD_AC() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "OPEN_DR_AC = :TDRSMST.OPEN_DR_AC "
            + "AND PROD_CD = '2206070101' "
            + "AND PRDAC_CD = '037'";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
            }
        }
    }
    public void T000_STARTBR_TDTSMST_BY_TD_AC() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND PROD_CD = '2206070101' "
            + "AND PRDAC_CD = '037'";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
            }
        }
    }
    public void T000_STARTBR_TDTSMST_BY_OCAC() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "PROD_CD = '2206070101' "
            + "AND PRDAC_CD = '037' "
            + "AND ( OPEN_DATE BETWEEN :WS_BEG_DT "
            + "AND :WS_END_DT )";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
            }
        }
    }
    public void T000_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_READ_TDTINST() throws IOException,SQLException,Exception {
        TDTINST_RD = new DBParm();
        TDTINST_RD.TableName = "TDTINST";
        IBS.READ(SCCGWA, TDRINST, TDTINST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READ_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        IBS.READ(SCCGWA, TDROTHE, TDTOTHE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READ_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
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
