package com.hisun.BP;

import java.util.ArrayList;
import java.util.List;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFTBVC {
    BPZFTBVC_WS_BV_INFO WS_BV_INFO;
    String CPN_R_MGM_TBVD = "BP-R-MGM-TBVD";
    String CPN_S_SOLD_CANCEL = "BP-S-SOLD-CANCEL    ";
    String CPN_R_BRW_TBVD = "BP-R-BRW-TBVD";
    String CPN_R_MGM_TBV = "BP-R-MGM-TBV";
    int K_MAX_PAR_CNT = 99;
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY";
    String CPN_F_BVLT_CHK = "BP-F-BVLT-CHK       ";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_I = 0;
    int WS_K = 0;
    char WS_FLG = ' ';
    String WS_TEMP_PLBOX_NO = " ";
    int WS_NUM = 0;
    List<BPZFTBVC_WS_BV_INFO> WS_BV_INFO = new ArrayList<BPZFTBVC_WS_BV_INFO>();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPCRTBVD BPCRTBVD = new BPCRTBVD();
    BPCRTBDB BPCRTBDB = new BPCRTBDB();
    BPCRTBV BPCRTBV = new BPCRTBV();
    BPRTBV BPRTBV = new BPRTBV();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCFLTCK BPCFLTCK = new BPCFLTCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCFTBVC BPCFTBVC;
    public void MP(SCCGWA SCCGWA, BPCFTBVC BPCFTBVC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFTBVC = BPCFTBVC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFTBVC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B100_CHK_INPUT_CN();
        } else {
            B100_CHK_INPUT();
        }
    }
    public void B100_CHK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRVHPB.POLL_BOX_IND = BPCFTBVC.VB_FLG;
        BPRVHPB.RELATE_FLG = 'Y';
        BPRVHPB.STS = 'N';
        BPCRVHPB.INFO.FUNC = 'F';
        S000_CALL_BPZRVHPB();
        CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
        CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
        if (BPCRVHPB.RETURN_INFO == 'F') {
            WS_TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
            CEP.TRC(SCCGWA, WS_TEMP_PLBOX_NO);
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BV_TLR;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPRTBVD);
        IBS.init(SCCGWA, BPCRTBDB);
        BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
        CEP.TRC(SCCGWA, "654321");
        if (BPCFTBVC.BV_FLG == '0') {
            BPRTBVD.TYPE = '0';
            BPCRTBDB.INFO.FUNC = 'H';
        } else {
            BPRTBVD.TYPE = '1';
            BPRTBVD.KEY.STS = '0';
            BPCRTBDB.INFO.FUNC = 'I';
        }
        S000_CALL_BPZRTBDB();
        IBS.init(SCCGWA, BPCRTBDB);
        BPCRTBDB.INFO.FUNC = 'N';
        S000_CALL_BPZRTBDB();
        CEP.TRC(SCCGWA, "1111111");
        WS_CNT = 0;
        while (BPCRTBDB.RETURN_INFO != 'N') {
            WS_CNT += 1;
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
            CEP.TRC(SCCGWA, BPRTBVD.NUM);
            CEP.TRC(SCCGWA, "22222");
            WS_BV_INFO = WS_BV_INFO.get(WS_CNT-1);
            WS_BV_INFO.WS_BV_CODE = BPRTBVD.KEY.BV_CODE;
            WS_BV_INFO.set(WS_CNT-1, WS_BV_INFO);
            if (BPCFTBVC.BV_FLG == '0') {
                WS_BV_INFO = WS_BV_INFO.get(WS_CNT-1);
                WS_BV_INFO.WS_BV_STS = BPRTBVD.KEY.STS;
                WS_BV_INFO.set(WS_CNT-1, WS_BV_INFO);
            } else {
                WS_BV_INFO = WS_BV_INFO.get(WS_CNT-1);
                WS_BV_INFO.WS_BV_VAL = BPRTBVD.KEY.VALUE;
                WS_BV_INFO.set(WS_CNT-1, WS_BV_INFO);
            }
            WS_BV_INFO = WS_BV_INFO.get(WS_CNT-1);
            WS_BV_INFO.WS_BV_NUM = BPRTBVD.NUM;
            WS_BV_INFO.set(WS_CNT-1, WS_BV_INFO);
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'N';
            S000_CALL_BPZRTBDB();
        }
        IBS.init(SCCGWA, BPCRTBDB);
        BPCRTBDB.INFO.FUNC = 'E';
        S000_CALL_BPZRTBDB();
        for (WS_I = 1; WS_I <= WS_CNT; WS_I += 1) {
            WS_FLG = '1';
            CEP.TRC(SCCGWA, WS_K);
            CEP.TRC(SCCGWA, WS_BV_INFO.get(WS_I-1).WS_BV_CODE);
            CEP.TRC(SCCGWA, WS_BV_INFO.get(WS_I-1).WS_BV_NUM);
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = WS_BV_INFO.get(WS_I-1).WS_BV_CODE;
            S000_CALL_BPZFBVQU();
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.B_LMT);
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.V_LMT);
            for (WS_K = 1; WS_K <= BPCFTBVC.IN_CNT 
                && WS_FLG != '0'; WS_K += 1) {
                CEP.TRC(SCCGWA, "AAAAAA");
                CEP.TRC(SCCGWA, WS_K);
                CEP.TRC(SCCGWA, BPCFTBVC.INFO[WS_K-1].BV_CODE);
                CEP.TRC(SCCGWA, BPCFTBVC.INFO[WS_K-1].BV_STS);
                CEP.TRC(SCCGWA, BPCFTBVC.INFO[WS_K-1].NUM);
                CEP.TRC(SCCGWA, "BBBBBB");
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, WS_BV_INFO.get(WS_I-1).WS_BV_CODE);
                CEP.TRC(SCCGWA, WS_BV_INFO.get(WS_I-1).WS_BV_STS);
                CEP.TRC(SCCGWA, WS_BV_INFO.get(WS_I-1).WS_BV_NUM);
                if ((BPCFTBVC.INFO[WS_K-1].BV_CODE.equalsIgnoreCase(WS_BV_INFO.get(WS_I-1).WS_BV_CODE) 
                    && (BPCFTBVC.INFO[WS_K-1].BV_STS == WS_BV_INFO.get(WS_I-1).WS_BV_STS 
                    && BPCFTBVC.INFO[WS_K-1].BV_STS != ' ' 
                    && BPCFTBVC.BV_FLG == '0')) 
                    || (BPCFTBVC.INFO[WS_K-1].BV_CODE.equalsIgnoreCase(WS_BV_INFO.get(WS_I-1).WS_BV_CODE) 
                    && (BPCFTBVC.INFO[WS_K-1].BV_VAL == WS_BV_INFO.get(WS_I-1).WS_BV_VAL 
                    && BPCFTBVC.INFO[WS_K-1].BV_VAL != 0 
                    && BPCFTBVC.BV_FLG == '1'))) {
                    CEP.TRC(SCCGWA, "FFFFFFFFFFFF");
                    if (WS_BV_INFO.get(WS_I-1).WS_BV_NUM > BPCFTBVC.INFO[WS_K-1].NUM) {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NUM_TOO_SMALL, BPCFTBVC.RC);
                        BPCFTBVC.RT_CNT = WS_K;
                    }
                    if (WS_BV_INFO.get(WS_I-1).WS_BV_NUM < BPCFTBVC.INFO[WS_K-1].NUM) {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NUM_TOO_LARGE, BPCFTBVC.RC);
                        BPCFTBVC.RT_CNT = WS_K;
                    }
                    if ((BPCFTBVC.VB_FLG == '0' 
                        && WS_BV_INFO.get(WS_I-1).WS_BV_NUM < BPCFBVQU.TX_DATA.B_LMT) 
                        || (BPCFTBVC.VB_FLG == '1' 
                        && WS_BV_INFO.get(WS_I-1).WS_BV_NUM < BPCFBVQU.TX_DATA.V_LMT)) {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_LESS_LOW_LIMIT, BPCFTBVC.RC);
                        BPCFTBVC.RT_CNT = WS_K;
                    }
                    if ((BPCFTBVC.VB_FLG == '0' 
                        && WS_BV_INFO.get(WS_I-1).WS_BV_NUM > BPCFBVQU.TX_DATA.B_LMTUP) 
                        || (BPCFTBVC.VB_FLG == '1' 
                        && WS_BV_INFO.get(WS_I-1).WS_BV_NUM > BPCFBVQU.TX_DATA.V_LMTUP)) {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_LARGE_UP_LIMIT, BPCFTBVC.RC);
                        BPCFTBVC.RT_CNT = WS_K;
                    }
                    WS_FLG = '0';
                } else {
                    WS_FLG = '1';
                }
            }
            if (WS_FLG == '1') {
                if (BPCFTBVC.RT_CNT == 0) {
                    BPCFTBVC.RT_CNT = WS_K;
                }
                CEP.TRC(SCCGWA, "12341234");
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RCD_NOT_ENOUGH, BPCFTBVC.RC);
            }
        }
        CEP.TRC(SCCGWA, BPCFTBVC.IN_CNT);
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, WS_TEMP_PLBOX_NO);
        if (BPCFTBVC.IN_CNT != WS_CNT) {
            for (WS_K = 1; WS_K <= BPCFTBVC.IN_CNT 
                && WS_FLG != '1'; WS_K += 1) {
                WS_FLG = '1';
                CEP.TRC(SCCGWA, BPCFTBVC.INFO[WS_K-1].BV_CODE);
                CEP.TRC(SCCGWA, BPCFTBVC.INFO[WS_K-1].NUM);
                for (WS_I = 1; WS_I <= WS_CNT 
                    && WS_FLG != '0'; WS_I += 1) {
                    CEP.TRC(SCCGWA, WS_BV_INFO.get(WS_I-1).WS_BV_CODE);
                    CEP.TRC(SCCGWA, WS_BV_INFO.get(WS_I-1).WS_BV_NUM);
                    if ((WS_BV_INFO.get(WS_I-1).WS_BV_CODE.equalsIgnoreCase(BPCFTBVC.INFO[WS_K-1].BV_CODE) 
                        && (WS_BV_INFO.get(WS_I-1).WS_BV_STS == BPCFTBVC.INFO[WS_K-1].BV_STS 
                        && BPCFTBVC.INFO[WS_K-1].BV_STS != ' ' 
                        && BPCFTBVC.BV_FLG == '0')) 
                        || (WS_BV_INFO.get(WS_I-1).WS_BV_CODE.equalsIgnoreCase(BPCFTBVC.INFO[WS_K-1].BV_CODE) 
                        && (WS_BV_INFO.get(WS_I-1).WS_BV_VAL == BPCFTBVC.INFO[WS_K-1].BV_VAL 
                        && BPCFTBVC.INFO[WS_K-1].BV_VAL != 0 
                        && BPCFTBVC.BV_FLG == '1'))) {
                        if (WS_BV_INFO.get(WS_I-1).WS_BV_NUM > BPCFTBVC.INFO[WS_K-1].NUM) {
                            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NUM_TOO_SMALL, BPCFTBVC.RC);
                            BPCFTBVC.RT_CNT = WS_I;
                        }
                        if (WS_BV_INFO.get(WS_I-1).WS_BV_NUM < BPCFTBVC.INFO[WS_K-1].NUM) {
                            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NUM_TOO_LARGE, BPCFTBVC.RC);
                            BPCFTBVC.RT_CNT = WS_I;
                        }
                        WS_FLG = '0';
                        CEP.TRC(SCCGWA, "FOUND");
                    } else {
                        WS_FLG = '1';
                        BPCFTBVC.RT_CNT = WS_I;
                        CEP.TRC(SCCGWA, WS_I);
                    }
                }
                if (WS_FLG == '1') {
                    if (BPCFTBVC.RT_CNT == 0) {
                        BPCFTBVC.RT_CNT = WS_I;
                    }
                    CEP.TRC(SCCGWA, BPCFTBVC.RT_CNT);
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TBV_REC_NOTFND, BPCFTBVC.RC);
                }
            }
        }
    }
    public void B100_CHK_INPUT_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (BPCFTBVC.REP_TLR.trim().length() == 0) {
            BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
        } else {
            BPRVHPB.CUR_TLR = BPCFTBVC.REP_TLR;
        }
        BPRVHPB.POLL_BOX_IND = BPCFTBVC.VB_FLG;
        BPRVHPB.RELATE_FLG = 'Y';
        BPRVHPB.STS = 'N';
        BPCRVHPB.INFO.FUNC = 'L';
        S000_CALL_BPZRVHPB();
        CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
        CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
        if (BPCRVHPB.RETURN_INFO == 'F') {
            WS_TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
            CEP.TRC(SCCGWA, WS_TEMP_PLBOX_NO);
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BV_TLR;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPRTBVD);
        IBS.init(SCCGWA, BPCRTBDB);
        BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
        CEP.TRC(SCCGWA, "123456");
        if (BPCFTBVC.BV_FLG == '0') {
            BPRTBVD.TYPE = '0';
            BPCRTBDB.INFO.FUNC = 'H';
        } else {
            BPRTBVD.TYPE = '1';
            BPRTBVD.KEY.STS = '0';
            BPCRTBDB.INFO.FUNC = 'I';
        }
        S000_CALL_BPZRTBDB();
        IBS.init(SCCGWA, BPCRTBDB);
        BPCRTBDB.INFO.FUNC = 'N';
        S000_CALL_BPZRTBDB();
        CEP.TRC(SCCGWA, "1111111");
        WS_CNT = 0;
        while (BPCRTBDB.RETURN_INFO != 'N') {
            WS_CNT += 1;
            WS_BV_INFO = WS_BV_INFO.get(WS_CNT-1);
            WS_BV_INFO.WS_BV_CODE = BPRTBVD.KEY.BV_CODE;
            WS_BV_INFO.set(WS_CNT-1, WS_BV_INFO);
            WS_BV_INFO = WS_BV_INFO.get(WS_CNT-1);
            WS_BV_INFO.WS_HEAD_NO = BPRTBVD.KEY.HEAD_NO;
            WS_BV_INFO.set(WS_CNT-1, WS_BV_INFO);
            if (BPCFTBVC.BV_FLG == '0') {
                WS_BV_INFO = WS_BV_INFO.get(WS_CNT-1);
                WS_BV_INFO.WS_BV_STS = BPRTBVD.KEY.STS;
                WS_BV_INFO.set(WS_CNT-1, WS_BV_INFO);
            } else {
                WS_BV_INFO = WS_BV_INFO.get(WS_CNT-1);
                WS_BV_INFO.WS_BV_VAL = BPRTBVD.KEY.VALUE;
                WS_BV_INFO.set(WS_CNT-1, WS_BV_INFO);
            }
            WS_BV_INFO = WS_BV_INFO.get(WS_CNT-1);
            WS_BV_INFO.WS_BV_NUM = BPRTBVD.NUM;
            WS_BV_INFO.set(WS_CNT-1, WS_BV_INFO);
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'N';
            S000_CALL_BPZRTBDB();
        }
        IBS.init(SCCGWA, BPCRTBDB);
        BPCRTBDB.INFO.FUNC = 'E';
        S000_CALL_BPZRTBDB();
        for (WS_I = 1; WS_I <= WS_CNT; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, WS_CNT);
            WS_FLG = '1';
            CEP.TRC(SCCGWA, "AAAA");
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = WS_BV_INFO.get(WS_I-1).WS_BV_CODE;
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.KEY.CODE);
            S000_CALL_BPZFBVQU();
            for (WS_K = 1; WS_K <= BPCFTBVC.IN_CNT 
                && WS_FLG != '0'; WS_K += 1) {
                if ((BPCFTBVC.INFO[WS_K-1].BV_CODE.equalsIgnoreCase(WS_BV_INFO.get(WS_I-1).WS_BV_CODE) 
                    && (BPCFTBVC.INFO[WS_K-1].BV_STS == WS_BV_INFO.get(WS_I-1).WS_BV_STS 
                    && (BPCFTBVC.INFO[WS_K-1].HEAD_NO.equalsIgnoreCase(WS_BV_INFO.get(WS_I-1).WS_HEAD_NO) 
                    && BPCFTBVC.INFO[WS_K-1].BV_STS != ' ' 
                    && BPCFTBVC.BV_FLG == '0'))) 
                    || (BPCFTBVC.INFO[WS_K-1].BV_CODE.equalsIgnoreCase(WS_BV_INFO.get(WS_I-1).WS_BV_CODE) 
                    && (BPCFTBVC.INFO[WS_K-1].BV_VAL == WS_BV_INFO.get(WS_I-1).WS_BV_VAL 
                    && BPCFTBVC.INFO[WS_K-1].BV_VAL != 0 
                    && BPCFTBVC.BV_FLG == '1'))) {
                    CEP.TRC(SCCGWA, "FFFFFFFFFFFF");
                    if (WS_BV_INFO.get(WS_I-1).WS_BV_NUM > BPCFTBVC.INFO[WS_K-1].NUM) {
                        CEP.TRC(SCCGWA, WS_BV_INFO.get(WS_I-1).WS_BV_NUM);
                        CEP.TRC(SCCGWA, BPCFTBVC.INFO[WS_K-1].NUM);
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NUM_TOO_SMALL, BPCFTBVC.RC);
                        BPCFTBVC.RT_CNT = WS_K;
                    }
                    if (WS_BV_INFO.get(WS_I-1).WS_BV_NUM < BPCFTBVC.INFO[WS_K-1].NUM) {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NUM_TOO_LARGE, BPCFTBVC.RC);
                        BPCFTBVC.RT_CNT = WS_K;
                    }
                    IBS.init(SCCGWA, BPCFLTCK);
                    BPCFLTCK.TX_DATA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    if (BPCFTBVC.REP_TLR.trim().length() == 0) {
                        BPCFLTCK.TX_DATA.TL_ID = SCCGWA.COMM_AREA.TL_ID;
                    } else {
                        BPCFLTCK.TX_DATA.TL_ID = BPCFTBVC.REP_TLR;
                    }
                    BPCFLTCK.TX_DATA.BV_CODE = WS_BV_INFO.get(WS_I-1).WS_BV_CODE;
                    BPCFLTCK.TX_DATA.VB_FLG = BPCFTBVC.VB_FLG;
                    BPCFLTCK.TX_DATA.PVAL = WS_BV_INFO.get(WS_I-1).WS_BV_VAL;
                    BPCFLTCK.TX_DATA.STS = WS_BV_INFO.get(WS_I-1).WS_BV_STS;
                    WS_FLG = '0';
                } else {
                    WS_FLG = '1';
                }
            }
            if (WS_FLG == '1' 
                && BPCFTBVC.CK_TYP == '1') {
                if (BPCFTBVC.RT_CNT == 0) {
                    BPCFTBVC.RT_CNT = WS_K;
                }
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RCD_NOT_ENOUGH, BPCFTBVC.RC);
            }
        }
        CEP.TRC(SCCGWA, WS_TEMP_PLBOX_NO);
        WS_FLG = ' ';
        for (WS_K = 1; WS_K <= BPCFTBVC.IN_CNT 
            && WS_FLG != '1'; WS_K += 1) {
            WS_FLG = '1';
            CEP.TRC(SCCGWA, BPCFTBVC.INFO[WS_K-1].BV_CODE);
            CEP.TRC(SCCGWA, BPCFTBVC.INFO[WS_K-1].NUM);
            for (WS_I = 1; WS_I <= WS_CNT 
                && WS_FLG != '0'; WS_I += 1) {
                CEP.TRC(SCCGWA, WS_BV_INFO.get(WS_I-1).WS_BV_CODE);
                CEP.TRC(SCCGWA, WS_BV_INFO.get(WS_I-1).WS_BV_NUM);
                CEP.TRC(SCCGWA, WS_BV_INFO.get(WS_I-1).WS_HEAD_NO);
                CEP.TRC(SCCGWA, BPCFTBVC.INFO[WS_K-1].HEAD_NO);
                if ((WS_BV_INFO.get(WS_I-1).WS_BV_CODE.equalsIgnoreCase(BPCFTBVC.INFO[WS_K-1].BV_CODE) 
                    && (WS_BV_INFO.get(WS_I-1).WS_BV_STS == BPCFTBVC.INFO[WS_K-1].BV_STS 
                    && (WS_BV_INFO.get(WS_I-1).WS_HEAD_NO.equalsIgnoreCase(BPCFTBVC.INFO[WS_K-1].HEAD_NO) 
                    && BPCFTBVC.INFO[WS_K-1].BV_STS != ' ' 
                    && BPCFTBVC.BV_FLG == '0'))) 
                    || (WS_BV_INFO.get(WS_I-1).WS_BV_CODE.equalsIgnoreCase(BPCFTBVC.INFO[WS_K-1].BV_CODE) 
                    && (WS_BV_INFO.get(WS_I-1).WS_BV_VAL == BPCFTBVC.INFO[WS_K-1].BV_VAL 
                    && BPCFTBVC.INFO[WS_K-1].BV_VAL != 0 
                    && BPCFTBVC.BV_FLG == '1'))) {
                    if (WS_BV_INFO.get(WS_I-1).WS_BV_NUM > BPCFTBVC.INFO[WS_K-1].NUM) {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NUM_TOO_SMALL, BPCFTBVC.RC);
                        BPCFTBVC.RT_CNT = WS_I;
                    }
                    if (WS_BV_INFO.get(WS_I-1).WS_BV_NUM < BPCFTBVC.INFO[WS_K-1].NUM) {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NUM_TOO_LARGE, BPCFTBVC.RC);
                        BPCFTBVC.RT_CNT = WS_I;
                    }
                    WS_FLG = '0';
                    CEP.TRC(SCCGWA, "FOUND");
                } else {
                    WS_FLG = '1';
                    BPCFTBVC.RT_CNT = WS_I;
                    CEP.TRC(SCCGWA, WS_I);
                }
            }
            if (WS_FLG == '1') {
                if (BPCFTBVC.RT_CNT == 0) {
                    BPCFTBVC.RT_CNT = WS_I;
                }
                CEP.TRC(SCCGWA, BPCFTBVC.RT_CNT);
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TBV_REC_NOTFND, BPCFTBVC.RC);
            }
        }
    }
    public void S000_CALL_BPZRTBDB() throws IOException,SQLException,Exception {
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_TBVD, BPCRTBDB);
        if (BPCRTBDB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTBDB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        BPCRVHPB.INFO.LEN = 152;
        IBS.CALLCPN(SCCGWA, CPN_R_BPTVHPB_MTN, BPCRVHPB);
        if (BPCRVHPB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRVHPB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTBVD() throws IOException,SQLException,Exception {
        BPCRTBVD.INFO.POINTER = BPRTBVD;
        BPCRTBVD.INFO.LEN = 160;
        IBS.CALLCPN(SCCGWA, CPN_R_MGM_TBVD, BPCRTBVD);
        if (BPCRTBVD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTBVD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTBV() throws IOException,SQLException,Exception {
        BPCRTBV.INFO.POINTER = BPRTBV;
        BPCRTBV.INFO.LEN = 30;
        IBS.CALLCPN(SCCGWA, CPN_R_MGM_TBV, BPCRTBV);
        if (BPCRTBV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTBV.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFLTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BVLT_CHK, BPCFLTCK);
        if (BPCFLTCK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFLTCK.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFTBVC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFTBVC = ");
            CEP.TRC(SCCGWA, BPCFTBVC);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
