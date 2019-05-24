package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3050 {
    int JIBS_tmp_int;
    String CPN_S_BV_B_TO_V = "BP-S-BV-B-TO-V   ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    int K_CNT = 4;
    int K_CNT_10 = 10;
    String CPN_S_NUM_CHK = "BP-S-BV-NO-CHK";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_BV_CNT = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    String WS_TEMP_PLBOX_NO = " ";
    char WS_BV_STS = ' ';
    String BPOT3050_FILLER1 = "BV CODE:";
    String WS_HIS_BVCODE = " ";
    String BPOT3050_FILLER3 = "HEAD NO:";
    String WS_HIS_HEADNO = " ";
    String BPOT3050_FILLER5 = "BEG NO:";
    String WS_HIS_BEGNO = " ";
    String BPOT3050_FILLER7 = "END NO:";
    String WS_HIS_ENDNO = " ";
    String BPOT3050_FILLER9 = "NUM NO:";
    int WS_HIS_NUMNO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBVBV BPCSBVBV = new BPCSBVBV();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCSNOCK BPCSNOCK = new BPCSNOCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3050_AWA_3050 BPB3050_AWA_3050;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3050 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3050_AWA_3050>");
        BPB3050_AWA_3050 = (BPB3050_AWA_3050) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3050_AWA_3050);
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.BV_DATA[1-1].BV_CODE);
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.BV_DATA[1-1].BV_NAME);
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.BV_DATA[1-1].HEAD_NO);
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.BV_DATA[1-1].BEG_NO);
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.BV_DATA[1-1].END_NO);
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.BV_DATA[1-1].BV_STS);
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.BV_DATA[1-1].NUM);
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.BV_DATA[2-1].BV_CODE);
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.BV_DATA[2-1].BV_NAME);
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.BV_DATA[2-1].HEAD_NO);
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.BV_DATA[2-1].BEG_NO);
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.BV_DATA[2-1].END_NO);
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.BV_DATA[2-1].BV_STS);
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.BV_DATA[2-1].NUM);
        B010_CHECK_BVSTS();
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_CN();
            B020_TLR_BV_TURN_IN_CN();
        } else {
            B010_CHECK_INPUT();
            B020_TLR_BV_TURN_IN();
        }
    }
    public void B010_CHECK_BVSTS() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= K_CNT 
            && BPB3050_AWA_3050.BV_DATA[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
            WS_BV_STS = BPB3050_AWA_3050.BV_DATA[WS_I-1].BV_STS;
            if ((WS_BV_STS != '0' 
                && WS_BV_STS != '1')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVSTS_INPERR;
                if (BPB3050_AWA_3050.BV_DATA[WS_I-1].BV_STS == ' ') WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(""+BPB3050_AWA_3050.BV_DATA[WS_I-1].BV_STS);
                S000_ERR_MSG_PROC();
            }
            if (BPB3050_AWA_3050.BV_DATA[WS_I-1].BV_STS == ' ') {
                BPB3050_AWA_3050.BV_DATA[WS_I-1].BV_STS = '0';
            }
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= K_CNT 
            && BPB3050_AWA_3050.BV_DATA[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
            WS_BV_CNT = WS_I;
        }
        CEP.TRC(SCCGWA, WS_BV_CNT);
        if (BPB3050_AWA_3050.BV_DATA[1-1].BV_CODE.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            WS_FLD_NO = BPB3050_AWA_3050.BV_DATA[1-1].BV_CODE_NO;
            S000_ERR_MSG_PROC();
        }
        for (WS_J = 1; WS_J <= 4 
            && BPB3050_AWA_3050.BV_DATA[WS_J-1].BV_CODE.trim().length() != 0; WS_J += 1) {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPB3050_AWA_3050.BV_DATA[WS_J-1].BV_CODE;
            S000_CALL_BPZFBVQU();
            if (BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0) {
                if (BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO == null) BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO += " ";
                for (WS_I = 1; WS_I <= 20 
                    && IBS.isNumeric(BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                if (BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO == null) BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO += " ";
                for (WS_I = 1; WS_I <= 20 
                    && IBS.isNumeric(BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
            if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
                if (BPB3050_AWA_3050.BV_DATA[WS_J-1].HEAD_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                    WS_FLD_NO = BPB3050_AWA_3050.BV_DATA[WS_J-1].HEAD_NO_NO;
                    S000_ERR_MSG_PROC();
                }
                if (BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0 
                    || BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                    WS_FLD_NO = BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
                || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
                if (BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO == null) BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO = "";
                JIBS_tmp_int = BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO += " ";
                if (BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
                else WS_COMP_BEGNO = Long.parseLong(BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN));
                if (BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO == null) BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO = "";
                JIBS_tmp_int = BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO += " ";
                if (BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
                else WS_COMP_ENDNO = Long.parseLong(BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN));
                if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                    WS_FLD_NO = BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                }
                BPCSNOCK.BV_CODE = BPB3050_AWA_3050.BV_DATA[1-1].BV_CODE;
                BPCSNOCK.BEG_NO = BPB3050_AWA_3050.BV_DATA[1-1].BEG_NO;
                BPCSNOCK.END_NO = BPB3050_AWA_3050.BV_DATA[1-1].END_NO;
                BPCSNOCK.NUM = BPB3050_AWA_3050.BV_DATA[1-1].NUM;
                CEP.TRC(SCCGWA, BPB3050_AWA_3050.BV_DATA[1-1].BEG_NO);
                CEP.TRC(SCCGWA, BPB3050_AWA_3050.BV_DATA[1-1].END_NO);
                CEP.TRC(SCCGWA, BPCSNOCK.NUM);
                BPCSNOCK.FUNC = '1';
                S000_CALL_BPZSNOCK();
            }
        }
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.TLR);
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.PSW_TYP);
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.TLRC_PSW);
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.TLRK_PSW);
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.BV_FUNC);
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.REP_TLR);
        if (BPB3050_AWA_3050.BV_FUNC == '0') {
            for (WS_I = 1; WS_I <= K_CNT_10 
                && BPB3050_AWA_3050.BV_DATA[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
                WS_BV_CNT = WS_I;
            }
            CEP.TRC(SCCGWA, WS_BV_CNT);
            if (BPB3050_AWA_3050.BV_DATA[1-1].BV_CODE.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
                WS_FLD_NO = BPB3050_AWA_3050.BV_DATA[1-1].BV_CODE_NO;
                S000_ERR_MSG_PROC();
            }
            for (WS_J = 1; WS_J <= K_CNT_10 
                && BPB3050_AWA_3050.BV_DATA[WS_J-1].BV_CODE.trim().length() != 0; WS_J += 1) {
                IBS.init(SCCGWA, BPCFBVQU);
                BPCFBVQU.TX_DATA.KEY.CODE = BPB3050_AWA_3050.BV_DATA[WS_J-1].BV_CODE;
                S000_CALL_BPZFBVQU();
                if (BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0) {
                    if (BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO == null) BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO = "";
                    JIBS_tmp_int = BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO += " ";
                    for (WS_I = 1; WS_I <= 20 
                        && IBS.isNumeric(BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                    }
                    if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                        WS_FLD_NO = BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
                if (BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                    if (BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO == null) BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO = "";
                    JIBS_tmp_int = BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO += " ";
                    for (WS_I = 1; WS_I <= 20 
                        && IBS.isNumeric(BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                    }
                    if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                        WS_FLD_NO = BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
                WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
                if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
                    if (BPB3050_AWA_3050.BV_DATA[WS_J-1].HEAD_NO.trim().length() > 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                        WS_FLD_NO = BPB3050_AWA_3050.BV_DATA[WS_J-1].HEAD_NO_NO;
                        S000_ERR_MSG_PROC();
                    }
                    if (BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO.trim().length() > 0 
                        || BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO.trim().length() > 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                        WS_FLD_NO = BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
                if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
                    || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
                    if (BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO == null) BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO = "";
                    JIBS_tmp_int = BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO += " ";
                    if (BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
                    else WS_COMP_BEGNO = Long.parseLong(BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO.substring(0, WS_BVNO_LEN));
                    if (BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO == null) BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO = "";
                    JIBS_tmp_int = BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO += " ";
                    if (BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
                    else WS_COMP_ENDNO = Long.parseLong(BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO.substring(0, WS_BVNO_LEN));
                    if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                        WS_FLD_NO = BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO_NO;
                        S000_ERR_MSG_PROC();
                    }
                    IBS.init(SCCGWA, BPCSNOCK);
                    BPCSNOCK.BV_CODE = BPB3050_AWA_3050.BV_DATA[WS_J-1].BV_CODE;
                    BPCSNOCK.BEG_NO = BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO;
                    BPCSNOCK.END_NO = BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO;
                    BPCSNOCK.NUM = BPB3050_AWA_3050.BV_DATA[WS_J-1].NUM;
                    CEP.TRC(SCCGWA, BPB3050_AWA_3050.BV_DATA[WS_J-1].BEG_NO);
                    CEP.TRC(SCCGWA, BPB3050_AWA_3050.BV_DATA[WS_J-1].END_NO);
                    CEP.TRC(SCCGWA, BPCSNOCK.NUM);
                    BPCSNOCK.FUNC = '1';
                    S000_CALL_BPZSNOCK();
                }
            }
        } else {
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.POLL_BOX_IND = '0';
            CEP.TRC(SCCGWA, BPB3050_AWA_3050.REP_TLR);
            if (BPB3050_AWA_3050.REP_TLR.trim().length() > 0) {
                BPRVHPB.CUR_TLR = BPB3050_AWA_3050.REP_TLR;
                if (BPB3050_AWA_3050.REP_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR187);
                } else {
                    IBS.init(SCCGWA, BPCFTLRQ);
                    BPCFTLRQ.INFO.TLR = BPB3050_AWA_3050.REP_TLR;
                    CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR);
                    S000_CALL_BPZFTLRQ();
                    CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_STSW);
                    if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                    JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                    for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                    if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                    JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                    for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                    if (BPCFTLRQ.INFO.TLR_STSW.substring(0, 1).equalsIgnoreCase("1") 
                        && BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("0")) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR188);
                    }
                    if (BPCFTLRQ.INFO.NEW_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR189);
                    }
                }
            } else {
                BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
            }
            CEP.TRC(SCCGWA, BPRVHPB.CUR_TLR);
            BPRVHPB.RELATE_FLG = 'Y';
            BPRVHPB.STS = 'N';
            BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CEP.TRC(SCCGWA, BPRVHPB.BR);
            BPCRVHPB.INFO.FUNC = 'F';
            S000_CALL_BPZRVHPB();
            CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
            CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
            if (BPCRVHPB.RETURN_INFO == 'F') {
                WS_TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
                CEP.TRC(SCCGWA, WS_TEMP_PLBOX_NO);
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_HVNT_BVB;
                S000_ERR_MSG_PROC();
            }
            if (BPRVHPB.BV_CHK_FLG == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_BOX_NOTBANL;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.CUR_TLR = BPB3050_AWA_3050.TLR;
            BPRVHPB.POLL_BOX_IND = '1';
            BPRVHPB.RELATE_FLG = 'Y';
            BPRVHPB.STS = 'N';
            BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCRVHPB.INFO.FUNC = 'L';
            S000_CALL_BPZRVHPB();
            if (BPCRVHPB.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BVLT_TLR;
                if (BPRVHPB.CUR_TLR.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(BPRVHPB.CUR_TLR);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
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
    public void B020_TLR_BV_TURN_IN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVBV);
        for (WS_I = 1; WS_I <= WS_BV_CNT; WS_I += 1) {
            BPCSBVBV.BV_DATA[WS_I-1].BV_CODE = BPB3050_AWA_3050.BV_DATA[WS_I-1].BV_CODE;
            BPCSBVBV.BV_DATA[WS_I-1].BV_NAME = BPB3050_AWA_3050.BV_DATA[WS_I-1].BV_NAME;
            BPCSBVBV.BV_DATA[WS_I-1].HEAD_NO = BPB3050_AWA_3050.BV_DATA[WS_I-1].HEAD_NO;
            BPCSBVBV.BV_DATA[WS_I-1].BEG_NO = BPB3050_AWA_3050.BV_DATA[WS_I-1].BEG_NO;
            BPCSBVBV.BV_DATA[WS_I-1].END_NO = BPB3050_AWA_3050.BV_DATA[WS_I-1].END_NO;
            BPCSBVBV.BV_DATA[WS_I-1].BV_STS = BPB3050_AWA_3050.BV_DATA[WS_I-1].BV_STS;
            BPCSBVBV.BV_DATA[WS_I-1].NUM = BPB3050_AWA_3050.BV_DATA[WS_I-1].NUM;
        }
        BPCSBVBV.RCV_TLR = BPB3050_AWA_3050.TLR;
        CEP.TRC(SCCGWA, BPB3050_AWA_3050.TLR);
        CEP.TRC(SCCGWA, BPCSBVBV.RCV_TLR);
        BPCSBVBV.PSW_TYP = BPB3050_AWA_3050.PSW_TYP;
        BPCSBVBV.TLRC_PSW = BPB3050_AWA_3050.TLRC_PSW;
        BPCSBVBV.TLRK_PSW = BPB3050_AWA_3050.TLRK_PSW;
        BPCSBVBV.CNT = WS_BV_CNT;
        S000_CALL_BPZSBVBV();
    }
    public void B020_TLR_BV_TURN_IN_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVBV);
        for (WS_I = 1; WS_I <= WS_BV_CNT; WS_I += 1) {
            BPCSBVBV.BV_DATA[WS_I-1].BV_CODE = BPB3050_AWA_3050.BV_DATA[WS_I-1].BV_CODE;
            BPCSBVBV.BV_DATA[WS_I-1].BV_NAME = BPB3050_AWA_3050.BV_DATA[WS_I-1].BV_NAME;
            BPCSBVBV.BV_DATA[WS_I-1].HEAD_NO = BPB3050_AWA_3050.BV_DATA[WS_I-1].HEAD_NO;
            BPCSBVBV.BV_DATA[WS_I-1].BEG_NO = BPB3050_AWA_3050.BV_DATA[WS_I-1].BEG_NO;
            BPCSBVBV.BV_DATA[WS_I-1].END_NO = BPB3050_AWA_3050.BV_DATA[WS_I-1].END_NO;
            BPCSBVBV.BV_DATA[WS_I-1].BV_STS = BPB3050_AWA_3050.BV_DATA[WS_I-1].BV_STS;
            BPCSBVBV.BV_DATA[WS_I-1].NUM = BPB3050_AWA_3050.BV_DATA[WS_I-1].NUM;
        }
        BPCSBVBV.RCV_TLR = BPB3050_AWA_3050.TLR;
        BPCSBVBV.PSW_TYP = BPB3050_AWA_3050.PSW_TYP;
        BPCSBVBV.TLRC_PSW = BPB3050_AWA_3050.TLRC_PSW;
        BPCSBVBV.TLRK_PSW = BPB3050_AWA_3050.TLRK_PSW;
        BPCSBVBV.CNT = WS_BV_CNT;
        BPCSBVBV.BV_FUNC = BPB3050_AWA_3050.BV_FUNC;
        BPCSBVBV.PB_NO = WS_TEMP_PLBOX_NO;
        BPCSBVBV.REP_TLR = BPB3050_AWA_3050.REP_TLR;
        S000_CALL_BPZSBVBV();
    }
    public void S000_CALL_BPZSNOCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_NUM_CHK, BPCSNOCK);
    }
    public void S000_CALL_BPZSBVBV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BV_B_TO_V, BPCSBVBV);
        if (BPCSBVBV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBVBV.RC);
            WS_FLD_NO = BPB3050_AWA_3050.BV_DATA[1-1].BV_CODE_NO;
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
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
