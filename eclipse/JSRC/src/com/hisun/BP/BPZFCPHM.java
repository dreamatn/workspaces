package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFCPHM {
    DBParm BPTFCTR_RD;
    DBParm BPTFSCH_RD;
    DBParm BPTFCPH_RD;
    String K_HIS_REMARK = "MAINTAIN FEE CALCULATION PARAMETER";
    String K_HIS_COPYBOOK = "BPCOFCPH";
    String K_CTRT_TYPE_FEES = "FEES";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_J = 0;
    char WS_FOUND_FLG = ' ';
    char WS_END_FLG = ' ';
    short WS_IDX = 0;
    String K_FMT_OUT = "BPX01";
    String K_FMT_OUT_1 = "BP073";
    String K_FMT_OUT_2 = "BP090";
    BPRFCTR BPRFCTR = new BPRFCTR();
    BPRFCPH BPRFCPH = new BPRFCPH();
    BPRFSCH BPRFSCH = new BPRFSCH();
    BPCOFCPH BPCHFCPH = new BPCOFCPH();
    BPCOFCPH BPCOFCPH = new BPCOFCPH();
    BPCPQCTR BPCPQCTR = new BPCPQCTR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCOCPHM BPCOCPHM = new BPCOCPHM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCSIC BPCSIC = new BPCSIC();
    SCCGWA SCCGWA;
    BPCFCPHM BPCFCPHM;
    public void MP(SCCGWA SCCGWA, BPCFCPHM BPCFCPHM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFCPHM = BPCFCPHM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFCPHM return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFCPH);
        IBS.init(SCCGWA, BPRFSCH);
        IBS.init(SCCGWA, BPRFCTR);
        IBS.init(SCCGWA, BPCOCPHM);
        BPRFCPH.KEY.CTRT_NO = BPCFCPHM.CTRT_NO;
        BPRFCPH.KEY.VALUE_DATE = BPCFCPHM.VALUE_DATE;
        BPCFCPHM.FILLER1 = 0X02;
        CEP.TRC(SCCGWA, BPCFCPHM.CTRT_NO);
        CEP.TRC(SCCGWA, BPCFCPHM.VALUE_DATE);
        CEP.TRC(SCCGWA, BPCFCPHM.PRICE_MTH);
        CEP.TRC(SCCGWA, BPCFCPHM.INT_BAS);
        CEP.TRC(SCCGWA, BPCFCPHM.AGGR_TYPE);
        CEP.TRC(SCCGWA, BPCFCPHM.REF_CCY);
        CEP.TRC(SCCGWA, BPCFCPHM.REF_MTH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCFCPHM.FUNCI != ' ') {
            CEP.TRC(SCCGWA, "SEND BY 9999158");
            if (BPCFCPHM.FUNCI == 'Q') {
                B500_FUNC_INQUIRY();
            } else if (BPCFCPHM.FUNCI == 'R') {
                B500_FUNC_INQUIRY1();
            } else {
            }
        } else {
            CEP.TRC(SCCGWA, "ACT AS SERVICE");
            B100_INPUT_CHECK();
            if (BPCFCPHM.FUNC == 'A') {
                B200_FUNC_ADD();
            } else if (BPCFCPHM.FUNC == 'U') {
                B300_FUNC_UPDATE();
            } else if (BPCFCPHM.FUNC == 'D') {
                B400_FUNC_DELETE();
            } else if (BPCFCPHM.FUNC == 'I') {
                B500_FUNC_INQ();
            } else {
            }
        }
    }
    public void B100_INPUT_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQCTR);
        BPCPQCTR.KEY.CTRT_NO = BPCFCPHM.CTRT_NO;
        S000_CALL_BPZPQCTR();
        CEP.TRC(SCCGWA, BPCFCPHM.VALUE_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if ((BPCFCPHM.FUNC == 'A') 
            && BPCFCPHM.VALUE_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFFDT_LT_ACDT;
            S000_ERR_MSG_PROC();
            CEP.TRC(SCCGWA, "DATE ERROR");
        }
        if (BPCFCPHM.FUNC == 'D') {
            R000_MOVE_TO_BPRFSCH();
            T000_READ_BPTFSCH();
            if (BPCFCPHM.VALUE_DATE <= BPRFSCH.START_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAN_NOT_DEL_ALL;
                S000_ERR_MSG_PROC();
            }
        }
        if ((BPCFCPHM.FUNC != 'A' 
            && BPCFCPHM.FUNC != 'U' 
            && BPCFCPHM.FUNC != 'D' 
            && BPCFCPHM.FUNC != 'I')) {
            CEP.TRC(SCCGWA, "BEFORE BP1011");
            CEP.TRC(SCCGWA, BPCFCPHM.FUNC);
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (BPCFCPHM.FUNC == 'A' 
            || BPCFCPHM.FUNC == 'U') {
            B110_ADD_UPDATE_CHECK();
        }
    }
    public void B110_ADD_UPDATE_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFCPHM.PRICE_MTH);
        if (BPCFCPHM.PRICE_MTH != '0' 
            && BPCFCPHM.PRICE_MTH != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            CEP.TRC(SCCGWA, BPCFCPHM.PRICE_MTH);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCFCPHM.INT_BAS);
        if (BPCFCPHM.PRICE_MTH == '1' 
            && (!BPCFCPHM.INT_BAS.equalsIgnoreCase("00") 
            && !BPCFCPHM.INT_BAS.equalsIgnoreCase("01") 
            && !BPCFCPHM.INT_BAS.equalsIgnoreCase("02") 
            && !BPCFCPHM.INT_BAS.equalsIgnoreCase("03"))) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCFCPHM.AGGR_TYPE);
        if ((BPCFCPHM.AGGR_TYPE != '0' 
            && BPCFCPHM.AGGR_TYPE != '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (BPCFCPHM.PRICE_MTH == '0' 
            && BPCFCPHM.AGGR_TYPE == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCFCPHM.REF_MTH);
        WS_FOUND_FLG = ' ';
        WS_END_FLG = ' ';
        for (WS_I = 1; WS_I <= 5 
            && WS_END_FLG != 'Y'; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPCFCPHM.RATE_TBL[WS_I-1].UP_AMT);
            CEP.TRC(SCCGWA, BPCFCPHM.RATE_TBL[WS_I-1].UP_PCT);
            if (WS_FOUND_FLG == 'Y') {
                if (BPCFCPHM.REF_MTH == '0' 
                    && BPCFCPHM.RATE_TBL[WS_I-1].UP_AMT != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                    S000_ERR_MSG_PROC();
                    WS_END_FLG = 'Y';
                    CEP.TRC(SCCGWA, "1");
                }
                if (BPCFCPHM.REF_MTH == '1' 
                    && BPCFCPHM.RATE_TBL[WS_I-1].UP_PCT != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                    S000_ERR_MSG_PROC();
                    WS_END_FLG = 'Y';
                    CEP.TRC(SCCGWA, "1.1");
                }
                if (BPCFCPHM.RATE_TBL[WS_I-1].FEE_AMT != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                    S000_ERR_MSG_PROC();
                    WS_END_FLG = 'Y';
                    CEP.TRC(SCCGWA, "2");
                }
                if (BPCFCPHM.RATE_TBL[WS_I-1].FEE_RATE != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                    S000_ERR_MSG_PROC();
                    WS_END_FLG = 'Y';
                    CEP.TRC(SCCGWA, "3");
                }
            } else {
                if ((BPCFCPHM.REF_MTH == '0' 
                    && BPCFCPHM.RATE_TBL[WS_I-1].UP_AMT == 0) 
                    || (BPCFCPHM.REF_MTH == '1' 
                    && BPCFCPHM.RATE_TBL[WS_I-1].UP_PCT == 0)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                    S000_ERR_MSG_PROC();
                    WS_END_FLG = 'Y';
                    CEP.TRC(SCCGWA, "4");
                }
                if ((BPCFCPHM.REF_MTH == '0' 
                    && BPCFCPHM.RATE_TBL[WS_I-1].UP_AMT >= 9999999999999.99) 
                    || (BPCFCPHM.REF_MTH == '1' 
                    && BPCFCPHM.RATE_TBL[WS_I-1].UP_PCT >= 999.99)) {
                    WS_FOUND_FLG = 'Y';
                    CEP.TRC(SCCGWA, "4.1");
                }
                if (BPCFCPHM.PRICE_MTH == '0' 
                    && BPCFCPHM.RATE_TBL[WS_I-1].FEE_RATE != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                    S000_ERR_MSG_PROC();
                    WS_END_FLG = 'Y';
                    CEP.TRC(SCCGWA, "5");
                    CEP.TRC(SCCGWA, BPCFCPHM.PRICE_MTH);
                    CEP.TRC(SCCGWA, BPCFCPHM.RATE_TBL[WS_I-1].FEE_RATE);
                }
                if (BPCFCPHM.PRICE_MTH == '1' 
                    && BPCFCPHM.RATE_TBL[WS_I-1].FEE_AMT != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                    S000_ERR_MSG_PROC();
                    WS_END_FLG = 'Y';
                    CEP.TRC(SCCGWA, "6");
                }
                WS_J = WS_I - 1;
                if (WS_I > 1 
                    && ((BPCFCPHM.REF_MTH == '0' 
                    && BPCFCPHM.RATE_TBL[WS_I-1].UP_AMT <= BPCFCPHM.RATE_TBL[WS_J-1].UP_AMT) 
                    || (BPCFCPHM.REF_MTH == '1' 
                    && BPCFCPHM.RATE_TBL[WS_I-1].UP_PCT <= BPCFCPHM.RATE_TBL[WS_J-1].UP_PCT))) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                    S000_ERR_MSG_PROC();
                    WS_END_FLG = 'Y';
                    CEP.TRC(SCCGWA, "7");
                }
            }
        }
        if (WS_FOUND_FLG == ' ' 
            && WS_END_FLG == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            CEP.TRC(SCCGWA, "8");
        }
    }
    public void B200_FUNC_ADD() throws IOException,SQLException,Exception {
        R001_MOVE_TO_BPRFCPH();
        T000_WRITE_BPTFCPH();
        R001_TRANS_HIS_NEW_DATA();
        R000_MOVE_TO_BPRFSCH();
        T000_READ_BPTFSCH();
        R000_MOVE_FSCH_TO_BPCOCPHM();
        R000_MOVE_FCPHM_TO_BPCOCPHM();
        B080_HISTORY_PROCESS();
        B600_SEND_FMT();
    }
    public void B300_FUNC_UPDATE() throws IOException,SQLException,Exception {
        R000_MOVE_TO_BPRFCPH();
        T000_READ_UPDATE_BPTFCPH();
        R001_TRANS_HIS_OLD_DATA();
        R001_MOVE_TO_BPRFCPH();
        R001_TRANS_HIS_NEW_DATA();
        T000_REWRITE_BPTFCPH();
        R000_MOVE_TO_BPRFSCH();
        T000_READ_BPTFSCH();
        R000_MOVE_FSCH_TO_BPCOCPHM();
        R000_MOVE_FCPHM_TO_BPCOCPHM();
        B080_HISTORY_PROCESS();
        B600_SEND_FMT();
    }
    public void B400_FUNC_DELETE() throws IOException,SQLException,Exception {
        R000_MOVE_TO_BPRFCPH();
        T000_READ_UPDATE_BPTFCPH();
        T000_DELETE_BPTFCPH();
        R000_MOVE_TO_BPRFSCH();
        T000_READ_BPTFSCH();
        R000_MOVE_FSCH_TO_BPCOCPHM();
        R000_MOVE_FCPHM_TO_BPCOCPHM();
        R001_TRANS_HIS_OLD_DATA();
        B080_HISTORY_PROCESS();
        B600_SEND_FMT();
    }
    public void B500_FUNC_INQUIRY() throws IOException,SQLException,Exception {
        R000_MOVE_TO_BPRFSCH();
        T000_READ_BPTFSCH();
        R000_MOVE_TO_BPRFCPH();
        T000_READ_BPTFCPH();
        R000_MOVE_FSCH_TO_BPCOCPHM();
        R000_MOVE_FCPH_TO_BPCOCPHM();
        B600_SEND_FMT();
    }
    public void B500_FUNC_INQUIRY1() throws IOException,SQLException,Exception {
        R000_MOVE_TO_BPRFSCH();
        T000_READ_BPTFSCH();
        R000_MOVE_FSCH_TO_BPCOCPHM();
        R000_MOVE_FCPH_TO_BPCOCPHM();
        B600_SEND_FMT();
    }
    public void B500_FUNC_INQ() throws IOException,SQLException,Exception {
        R000_MOVE_TO_BPRFSCH();
        T000_READ_BPTFSCH();
        R000_MOVE_TO_BPRFCPH();
        T000_READ_BPTFCPH();
        R000_MOVE_FSCH_TO_BPCOCPHM();
        R000_MOVE_FCPH_TO_BPCOCPHM();
        B600_SEND_FMT();
    }
    public void R000_MOVE_TO_BPRFSCH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFCPHM.CTRT_NO);
        BPRFSCH.KEY.CTRT_NO = BPCFCPHM.CTRT_NO;
    }
    public void R000_MOVE_TO_BPRFCPH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFCPHM.CTRT_NO);
        CEP.TRC(SCCGWA, BPRFCPH.KEY.CTRT_NO);
        CEP.TRC(SCCGWA, BPCFCPHM.VALUE_DATE);
        BPRFCPH.KEY.CTRT_NO = BPCFCPHM.CTRT_NO;
        BPRFCPH.KEY.VALUE_DATE = BPCFCPHM.VALUE_DATE;
    }
    public void R001_MOVE_TO_BPRFCPH() throws IOException,SQLException,Exception {
        BPRFCPH.KEY.CTRT_NO = BPCFCPHM.CTRT_NO;
        BPRFCPH.KEY.VALUE_DATE = BPCFCPHM.VALUE_DATE;
        CEP.TRC(SCCGWA, BPCFCPHM.INT_BAS);
        if (BPCFCPHM.INT_BAS.trim().length() == 0) {
            BPCFCPHM.INT_BAS = "02";
        }
        if ((BPCFCPHM.INT_BAS.equalsIgnoreCase("01") 
            || BPCFCPHM.INT_BAS.equalsIgnoreCase("02") 
            || BPCFCPHM.INT_BAS.equalsIgnoreCase("03"))) {
            BPRFCPH.INT_BAS = BPCFCPHM.INT_BAS;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CALR_STD_ERR;
            S000_ERR_MSG_PROC();
        }
        BPRFCPH.MULTI = BPCFCPHM.MULTI;
        BPRFCPH.AGGR_TYPE = BPCFCPHM.AGGR_TYPE;
        BPRFCPH.REF_CCY = BPCFCPHM.REF_CCY;
        BPRFCPH.REF_METHOD = BPCFCPHM.REF_MTH;
        BPRFCPH.UP_AMT_1 = BPCFCPHM.RATE_TBL[1-1].UP_AMT;
        BPRFCPH.UP_PCT_1 = BPCFCPHM.RATE_TBL[1-1].UP_PCT;
        BPRFCPH.FEE_AMT_1 = BPCFCPHM.RATE_TBL[1-1].FEE_AMT;
        BPRFCPH.FEE_RATE_1 = BPCFCPHM.RATE_TBL[1-1].FEE_RATE;
        BPRFCPH.UP_AMT_2 = BPCFCPHM.RATE_TBL[2-1].UP_AMT;
        BPRFCPH.UP_PCT_2 = BPCFCPHM.RATE_TBL[2-1].UP_PCT;
        BPRFCPH.FEE_AMT_2 = BPCFCPHM.RATE_TBL[2-1].FEE_AMT;
        BPRFCPH.FEE_RATE_2 = BPCFCPHM.RATE_TBL[2-1].FEE_RATE;
        BPRFCPH.UP_AMT_3 = BPCFCPHM.RATE_TBL[3-1].UP_AMT;
        BPRFCPH.UP_PCT_3 = BPCFCPHM.RATE_TBL[3-1].UP_PCT;
        BPRFCPH.FEE_AMT_3 = BPCFCPHM.RATE_TBL[3-1].FEE_AMT;
        BPRFCPH.FEE_RATE_3 = BPCFCPHM.RATE_TBL[3-1].FEE_RATE;
        BPRFCPH.UP_AMT_4 = BPCFCPHM.RATE_TBL[4-1].UP_AMT;
        BPRFCPH.UP_PCT_4 = BPCFCPHM.RATE_TBL[4-1].UP_PCT;
        BPRFCPH.FEE_AMT_4 = BPCFCPHM.RATE_TBL[4-1].FEE_AMT;
        BPRFCPH.FEE_RATE_4 = BPCFCPHM.RATE_TBL[4-1].FEE_RATE;
        BPRFCPH.UP_AMT_5 = BPCFCPHM.RATE_TBL[5-1].UP_AMT;
        BPRFCPH.UP_PCT_5 = BPCFCPHM.RATE_TBL[5-1].UP_PCT;
        BPRFCPH.FEE_AMT_5 = BPCFCPHM.RATE_TBL[5-1].FEE_AMT;
        BPRFCPH.FEE_RATE_5 = BPCFCPHM.RATE_TBL[5-1].FEE_RATE;
        BPRFCPH.REMARK = BPCFCPHM.REMARK;
    }
    public void R000_MOVE_FSCH_TO_BPCOCPHM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCPHM);
        BPCOCPHM.FUNC = BPCFCPHM.FUNC;
        BPCOCPHM.CTRT_NO = BPRFSCH.KEY.CTRT_NO;
        BPCOCPHM.REL_CTRT_NO = BPRFSCH.REL_CTRT_NO;
        BPCOCPHM.PRD_TYPE = BPRFSCH.PRD_TYPE;
        BPCOCPHM.FEE_TYP = BPRFSCH.FEE_TYPE;
        BPCOCPHM.PAY_IND = BPRFSCH.PAY_IND;
        BPCOCPHM.PRICE_MTH = BPRFSCH.PRICE_METHOD;
        BPCOCPHM.AGGR_TYPE = BPRFSCH.AGGR_TYPE;
        BPCOCPHM.REF_CCY = BPRFSCH.REF_CCY;
        BPCOCPHM.REF_MTH = BPRFSCH.REF_METHOD;
        CEP.TRC(SCCGWA, BPCOCPHM.FUNC);
        CEP.TRC(SCCGWA, BPCOCPHM.CTRT_NO);
        CEP.TRC(SCCGWA, BPCOCPHM.REL_CTRT_NO);
        CEP.TRC(SCCGWA, BPCOCPHM.PRD_TYPE);
        CEP.TRC(SCCGWA, BPCOCPHM.FEE_TYP);
        CEP.TRC(SCCGWA, BPCOCPHM.PAY_IND);
        CEP.TRC(SCCGWA, BPCOCPHM.PRICE_MTH);
        CEP.TRC(SCCGWA, BPCOCPHM.AGGR_TYPE);
        CEP.TRC(SCCGWA, BPCOCPHM.REF_CCY);
        CEP.TRC(SCCGWA, BPCOCPHM.REF_MTH);
    }
    public void R000_MOVE_FCPH_TO_BPCOCPHM() throws IOException,SQLException,Exception {
        BPCOCPHM.INT_BAS = BPRFCPH.INT_BAS;
        BPCOCPHM.MULTI = BPRFCPH.MULTI;
        BPCOCPHM.VALUE_DATE = BPRFCPH.KEY.VALUE_DATE;
        BPCOCPHM.RATE_TBL[1-1].UP_AMT = BPRFCPH.UP_AMT_1;
        BPCOCPHM.RATE_TBL[1-1].UP_PCT = BPRFCPH.UP_PCT_1;
        BPCOCPHM.RATE_TBL[1-1].FEE_AMT = BPRFCPH.FEE_AMT_1;
        BPCOCPHM.RATE_TBL[1-1].FEE_RATE = BPRFCPH.FEE_RATE_1;
        BPCOCPHM.RATE_TBL[2-1].UP_AMT = BPRFCPH.UP_AMT_2;
        BPCOCPHM.RATE_TBL[2-1].UP_PCT = BPRFCPH.UP_PCT_2;
        BPCOCPHM.RATE_TBL[2-1].FEE_AMT = BPRFCPH.FEE_AMT_2;
        BPCOCPHM.RATE_TBL[2-1].FEE_RATE = BPRFCPH.FEE_RATE_2;
        BPCOCPHM.RATE_TBL[3-1].UP_AMT = BPRFCPH.UP_AMT_3;
        BPCOCPHM.RATE_TBL[3-1].UP_PCT = BPRFCPH.UP_PCT_3;
        BPCOCPHM.RATE_TBL[3-1].FEE_AMT = BPRFCPH.FEE_AMT_3;
        BPCOCPHM.RATE_TBL[3-1].FEE_RATE = BPRFCPH.FEE_RATE_3;
        BPCOCPHM.RATE_TBL[4-1].UP_AMT = BPRFCPH.UP_AMT_4;
        BPCOCPHM.RATE_TBL[4-1].UP_PCT = BPRFCPH.UP_PCT_4;
        BPCOCPHM.RATE_TBL[4-1].FEE_AMT = BPRFCPH.FEE_AMT_4;
        BPCOCPHM.RATE_TBL[4-1].FEE_RATE = BPRFCPH.FEE_RATE_4;
        BPCOCPHM.RATE_TBL[5-1].UP_AMT = BPRFCPH.UP_AMT_5;
        BPCOCPHM.RATE_TBL[5-1].UP_PCT = BPRFCPH.UP_PCT_5;
        BPCOCPHM.RATE_TBL[5-1].FEE_AMT = BPRFCPH.FEE_AMT_5;
        BPCOCPHM.RATE_TBL[5-1].FEE_RATE = BPRFCPH.FEE_RATE_5;
        BPCOCPHM.REMARK = BPRFCPH.REMARK;
        CEP.TRC(SCCGWA, BPCOCPHM.INT_BAS);
        CEP.TRC(SCCGWA, BPCOCPHM.MULTI);
        CEP.TRC(SCCGWA, BPCOCPHM.VALUE_DATE);
        CEP.TRC(SCCGWA, BPCOCPHM.RATE_TBL[1-1].UP_AMT);
        CEP.TRC(SCCGWA, BPCOCPHM.RATE_TBL[1-1].UP_PCT);
        CEP.TRC(SCCGWA, BPCOCPHM.RATE_TBL[1-1].FEE_AMT);
        CEP.TRC(SCCGWA, BPCOCPHM.RATE_TBL[1-1].FEE_RATE);
        CEP.TRC(SCCGWA, BPCOCPHM.REMARK);
    }
    public void R000_MOVE_FCPHM_TO_BPCOCPHM() throws IOException,SQLException,Exception {
        BPCOCPHM.VALUE_DATE = BPCFCPHM.VALUE_DATE;
        BPCOCPHM.INT_BAS = BPCFCPHM.INT_BAS;
        if (BPCFCPHM.FUNC == 'A') {
            BPCOCPHM.MULTI = 100;
            CEP.TRC(SCCGWA, "FCPHM-INT-BAS = 100");
        } else {
            BPCOCPHM.MULTI = BPCFCPHM.MULTI;
        }
        for (WS_IDX = 1; WS_IDX <= 5; WS_IDX += 1) {
            BPCOCPHM.RATE_TBL[WS_IDX-1].UP_AMT = BPCFCPHM.RATE_TBL[WS_IDX-1].UP_AMT;
            BPCOCPHM.RATE_TBL[WS_IDX-1].UP_PCT = BPCFCPHM.RATE_TBL[WS_IDX-1].UP_PCT;
            BPCOCPHM.RATE_TBL[WS_IDX-1].FEE_AMT = BPCFCPHM.RATE_TBL[WS_IDX-1].FEE_AMT;
            BPCOCPHM.RATE_TBL[WS_IDX-1].FEE_RATE = BPCFCPHM.RATE_TBL[WS_IDX-1].FEE_RATE;
            CEP.TRC(SCCGWA, WS_IDX);
            CEP.TRC(SCCGWA, BPCOCPHM.RATE_TBL[WS_IDX-1].UP_AMT);
        }
        BPCOCPHM.REMARK = BPCFCPHM.REMARK;
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        BPCPNHIS.INFO.REF_NO = BPRFCPH.KEY.CTRT_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 411;
        if (BPCFCPHM.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.NEW_DAT_PT = BPCHFCPH;
        }
        if (BPCFCPHM.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = BPCOFCPH;
            BPCPNHIS.INFO.NEW_DAT_PT = BPCHFCPH;
        }
        if (BPCFCPHM.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.OLD_DAT_PT = BPCOFCPH;
        }
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA);
        S000_CALL_BPZPNHIS();
    }
    public void R001_TRANS_HIS_OLD_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFCPH);
        BPCOFCPH.KEY.CTRT_NO = BPRFCPH.KEY.CTRT_NO;
        BPCOFCPH.KEY.VALUE_DATE = BPRFCPH.KEY.VALUE_DATE;
        BPCOFCPH.INT_BAS = BPRFCPH.INT_BAS;
        BPCOFCPH.MULTI = BPRFCPH.MULTI;
        BPCOFCPH.AGGR_TYPE = BPRFCPH.AGGR_TYPE;
        BPCOFCPH.REF_CCY = BPRFCPH.REF_CCY;
        BPCOFCPH.REF_METHOD = BPRFCPH.REF_METHOD;
        BPCOFCPH.UP_AMT_1 = BPRFCPH.UP_AMT_1;
        BPCOFCPH.UP_PCT_1 = BPRFCPH.UP_PCT_1;
        BPCOFCPH.FEE_AMT_1 = BPRFCPH.FEE_AMT_1;
        BPCOFCPH.FEE_RATE_1 = BPRFCPH.FEE_RATE_1;
        BPCOFCPH.UP_AMT_2 = BPRFCPH.UP_AMT_2;
        BPCOFCPH.UP_PCT_2 = BPRFCPH.UP_PCT_2;
        BPCOFCPH.FEE_AMT_2 = BPRFCPH.FEE_AMT_2;
        BPCOFCPH.FEE_RATE_2 = BPRFCPH.FEE_RATE_2;
        BPCOFCPH.UP_AMT_3 = BPRFCPH.UP_AMT_3;
        BPCOFCPH.UP_PCT_3 = BPRFCPH.UP_PCT_3;
        BPCOFCPH.FEE_AMT_3 = BPRFCPH.FEE_AMT_3;
        BPCOFCPH.FEE_RATE_3 = BPRFCPH.FEE_RATE_3;
        BPCOFCPH.UP_AMT_4 = BPRFCPH.UP_AMT_4;
        BPCOFCPH.UP_PCT_4 = BPRFCPH.UP_PCT_4;
        BPCOFCPH.FEE_AMT_4 = BPRFCPH.FEE_AMT_4;
        BPCOFCPH.FEE_RATE_4 = BPRFCPH.FEE_RATE_4;
        BPCOFCPH.UP_AMT_5 = BPRFCPH.UP_AMT_5;
        BPCOFCPH.UP_PCT_5 = BPRFCPH.UP_PCT_5;
        BPCOFCPH.FEE_AMT_5 = BPRFCPH.FEE_AMT_5;
        BPCOFCPH.FEE_RATE_5 = BPRFCPH.FEE_RATE_5;
        BPCOFCPH.REMARK = BPRFCPH.REMARK;
    }
    public void R001_TRANS_HIS_NEW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCHFCPH);
        BPCHFCPH.KEY.CTRT_NO = BPRFCPH.KEY.CTRT_NO;
        BPCHFCPH.KEY.VALUE_DATE = BPRFCPH.KEY.VALUE_DATE;
        BPCHFCPH.INT_BAS = BPRFCPH.INT_BAS;
        BPCHFCPH.MULTI = BPRFCPH.MULTI;
        BPCHFCPH.AGGR_TYPE = BPRFCPH.AGGR_TYPE;
        BPCHFCPH.REF_CCY = BPRFCPH.REF_CCY;
        BPCHFCPH.REF_METHOD = BPRFCPH.REF_METHOD;
        BPCHFCPH.UP_AMT_1 = BPRFCPH.UP_AMT_1;
        BPCHFCPH.UP_PCT_1 = BPRFCPH.UP_PCT_1;
        BPCHFCPH.FEE_AMT_1 = BPRFCPH.FEE_AMT_1;
        BPCHFCPH.FEE_RATE_1 = BPRFCPH.FEE_RATE_1;
        BPCHFCPH.UP_AMT_2 = BPRFCPH.UP_AMT_2;
        BPCHFCPH.UP_PCT_2 = BPRFCPH.UP_PCT_2;
        BPCHFCPH.FEE_AMT_2 = BPRFCPH.FEE_AMT_2;
        BPCHFCPH.FEE_RATE_2 = BPRFCPH.FEE_RATE_2;
        BPCHFCPH.UP_AMT_3 = BPRFCPH.UP_AMT_3;
        BPCHFCPH.UP_PCT_3 = BPRFCPH.UP_PCT_3;
        BPCHFCPH.FEE_AMT_3 = BPRFCPH.FEE_AMT_3;
        BPCHFCPH.FEE_RATE_3 = BPRFCPH.FEE_RATE_3;
        BPCHFCPH.UP_AMT_4 = BPRFCPH.UP_AMT_4;
        BPCHFCPH.UP_PCT_4 = BPRFCPH.UP_PCT_4;
        BPCHFCPH.FEE_AMT_4 = BPRFCPH.FEE_AMT_4;
        BPCHFCPH.FEE_RATE_4 = BPRFCPH.FEE_RATE_4;
        BPCHFCPH.UP_AMT_5 = BPRFCPH.UP_AMT_5;
        BPCHFCPH.UP_PCT_5 = BPRFCPH.UP_PCT_5;
        BPCHFCPH.FEE_AMT_5 = BPRFCPH.FEE_AMT_5;
        BPCHFCPH.FEE_RATE_5 = BPRFCPH.FEE_RATE_5;
        BPCHFCPH.REMARK = BPRFCPH.REMARK;
    }
    public void B600_SEND_FMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFCPHM.FUNC);
        IBS.init(SCCGWA, SCCFMT);
        if (BPCFCPHM.FUNC != ' ') {
            if (BPCFCPHM.FUNC == 'I') {
                CEP.TRC(SCCGWA, "BP071");
                SCCFMT.FMTID = K_FMT_OUT_2;
            } else {
                CEP.TRC(SCCGWA, "BP073");
                SCCFMT.FMTID = K_FMT_OUT_1;
            }
        } else {
            CEP.TRC(SCCGWA, "BPX01");
            SCCFMT.FMTID = K_FMT_OUT;
        }
        SCCFMT.DATA_PTR = BPCOCPHM;
        SCCFMT.DATA_LEN = 479;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_BPTFCTR() throws IOException,SQLException,Exception {
        BPTFCTR_RD = new DBParm();
        BPTFCTR_RD.TableName = "BPTFCTR";
        IBS.READ(SCCGWA, BPRFCTR, BPTFCTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_F_CTRT_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQCTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-FEECTRSCH", BPCPQCTR);
        if (BPCPQCTR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQCTR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_BPTFSCH() throws IOException,SQLException,Exception {
        BPTFSCH_RD = new DBParm();
        BPTFSCH_RD.TableName = "BPTFSCH";
        BPTFSCH_RD.upd = true;
        IBS.READ(SCCGWA, BPRFSCH, BPTFSCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_EXIST;
            S000_ERR_MSG_PROC();
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_BPTFCPH() throws IOException,SQLException,Exception {
        BPTFCPH_RD = new DBParm();
        BPTFCPH_RD.TableName = "BPTFCPH";
        BPTFCPH_RD.upd = true;
        IBS.READ(SCCGWA, BPRFCPH, BPTFCPH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FCPH_EXIST;
            S000_ERR_MSG_PROC();
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FCPH_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_UPDATE_BPTFCPH() throws IOException,SQLException,Exception {
        BPTFCPH_RD = new DBParm();
        BPTFCPH_RD.TableName = "BPTFCPH";
        BPTFCPH_RD.upd = true;
        IBS.READ(SCCGWA, BPRFCPH, BPTFCPH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FCPH_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_BPTFCPH() throws IOException,SQLException,Exception {
        BPTFCPH_RD = new DBParm();
        BPTFCPH_RD.TableName = "BPTFCPH";
        IBS.REWRITE(SCCGWA, BPRFCPH, BPTFCPH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FCPH_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_WRITE_BPTFCPH() throws IOException,SQLException,Exception {
        BPTFCPH_RD = new DBParm();
        BPTFCPH_RD.TableName = "BPTFCPH";
        BPTFCPH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFCPH, BPTFCPH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FCPH_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_DELETE_BPTFCPH() throws IOException,SQLException,Exception {
        BPTFCPH_RD = new DBParm();
        BPTFCPH_RD.TableName = "BPTFCPH";
        IBS.DELETE(SCCGWA, BPRFCPH, BPTFCPH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FCPH_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
