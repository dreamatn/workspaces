package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPOT1174 {
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP067";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String CPN_FSVR_MAINTAIN = "BP-F-S-MAINTAIN-FSVR";
    String CPN_F_T_FEE_INFO = "BP-F-T-FEE-INFO";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    short WS_FEE_NO = 2;
    short WS_FEE_NEXT = 0;
    String WS_DEF_FLG = " ";
    char WS_INPUT_ENDED = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFSVR BPCOFSVR = new BPCOFSVR();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    SCCGWA SCCGWA;
    BPB1110_AWA_1110 BPB1110_AWA_1110;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1174 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1110_AWA_1110>");
        BPB1110_AWA_1110 = (BPB1110_AWA_1110) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_CN();
            if (pgmRtn) return;
            B020_MODIFY_SVR_FEE_CN();
            if (pgmRtn) return;
        } else {
            B020_MODIFY_SVR_FEE();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        if (BPB1110_AWA_1110.SVR_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_SVR_NO_ERR;
            WS_FLD_NO = BPB1110_AWA_1110.SVR_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPB1110_AWA_1110.EFF_DT;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR;
            WS_FLD_NO = BPB1110_AWA_1110.EFF_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPB1110_AWA_1110.EXP_DT;
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EXP_DATE_ERR;
            WS_FLD_NO = BPB1110_AWA_1110.EXP_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BPB1110_AWA_1110.EXP_DT <= BPB1110_AWA_1110.EFF_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_GT_EXPDT;
            WS_FLD_NO = BPB1110_AWA_1110.EXP_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BPB1110_AWA_1110.AUT_FLG != '1' 
            && BPB1110_AWA_1110.AUT_FLG != '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_AUT_FLG_ERR;
            WS_FLD_NO = BPB1110_AWA_1110.AUT_FLG_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BPB1110_AWA_1110.FEE_INFO[1-1].FEE_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_FEE_FIRST;
            WS_FLD_NO = BPB1110_AWA_1110.FEE_INFO[1-1].FEE_CODE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_FEE_NEXT = 1;
        for (WS_FEE_NO = 2; WS_FEE_NO < 20; WS_FEE_NO += 1) {
            WS_FEE_NEXT = (short) (WS_FEE_NO + 1);
            if (BPB1110_AWA_1110.FEE_INFO[WS_FEE_NO-1].FEE_CODE.trim().length() == 0 
                && BPB1110_AWA_1110.FEE_INFO[WS_FEE_NEXT-1].FEE_CODE.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_FEE_INFO;
                WS_FLD_NO = BPB1110_AWA_1110.FEE_INFO[WS_FEE_NEXT-1].FEE_CODE_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        for (WS_CNT1 = 1; WS_CNT1 < 20 
            && BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].FEE_CODE.trim().length() != 0; WS_CNT1 += 1) {
            IBS.init(SCCGWA, BPRFBAS);
            IBS.init(SCCGWA, BPCTFBAS);
            BPRFBAS.KEY.FEE_CODE = BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].FEE_CODE;
            BPCTFBAS.INFO.FUNC = 'Q';
            BPCTFBAS.INFO.POINTER = BPRFBAS;
            BPCTFBAS.INFO.REC_LEN = 312;
            S000_CALL_BPZTFBAS();
            if (pgmRtn) return;
            if (BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].PRD_CODE.trim().length() > 0) {
                IBS.init(SCCGWA, BPCPQPRD);
                BPCPQPRD.PRDT_CODE = BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].PRD_CODE;
                S000_CALL_BPZPQPRD();
                if (pgmRtn) return;
            }
            if (BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].BNK_FLG != '0' 
                && BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].BNK_FLG != '1' 
                && BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].BNK_FLG != ' ') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_BNK_FLG_ERR;
                WS_FLD_NO = BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].BNK_FLG_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].CITY_FLG != '0' 
                && BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].CITY_FLG != '1' 
                && BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].CITY_FLG != ' ') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_CITY_FLG_ERR;
                WS_FLD_NO = BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].CITY_FLG_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].CI_FLG);
            if (BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].CI_FLG != '1' 
                && BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].CI_FLG != '2' 
                && BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].CI_FLG != '3' 
                && BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].CI_FLG != ' ') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_CI_FLG_ERR;
                WS_FLD_NO = BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].CI_FLG_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].BV_FLG);
            if (BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].BV_FLG != '1' 
                && BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].BV_FLG != '2' 
                && BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].BV_FLG != '3' 
                && BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].BV_FLG != '4' 
                && BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].BV_FLG != '6' 
                && BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].BV_FLG != ' ') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_BV_FLG_ERR;
                WS_FLD_NO = BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].BV_FLG_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].BVF_CODE.trim().length() > 0) {
                IBS.init(SCCGWA, BPCFBVQU);
                BPCFBVQU.TX_DATA.KEY.CODE = BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].BVF_CODE;
                S000_CALL_BPZFBVQU();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].DC_FLG);
            if (BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].DC_FLG != '0' 
                && BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].DC_FLG != '1' 
                && BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].DC_FLG != ' ') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_DC_FLG_ERR;
                WS_FLD_NO = BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].DC_FLG_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].DEF_FLG);
            if (BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].DEF_FLG.trim().length() > 0 
                && !BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].DEF_FLG.equalsIgnoreCase("00") 
                && !BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].DEF_FLG.equalsIgnoreCase("01") 
                && !BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].DEF_FLG.equalsIgnoreCase("02") 
                && !BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].DEF_FLG.equalsIgnoreCase("03") 
                && !BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].DEF_FLG.equalsIgnoreCase("04") 
                && !BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].DEF_FLG.equalsIgnoreCase("05")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_DEF_FLG_ERR;
                WS_FLD_NO = BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].DEF_FLG_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (BPB1110_AWA_1110.DMCR_FLG == ' ') {
            BPB1110_AWA_1110.DMCR_FLG = 'N';
        }
        CEP.TRC(SCCGWA, BPB1110_AWA_1110.MAT_FLG);
        if (BPB1110_AWA_1110.MAT_FLG == ' ') {
            BPB1110_AWA_1110.MAT_FLG = 'Y';
        }
    }
    public void B020_MODIFY_SVR_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFSVR);
        BPCOFSVR.FUNC = 'U';
        BPCOFSVR.OUTPUT_FMT = K_OUTPUT_FMT;
        S000_CALL_BPZFSSVR();
        if (pgmRtn) return;
    }
    public void B020_MODIFY_SVR_FEE_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFSVR);
        BPCOFSVR.FUNC = 'U';
        BPCOFSVR.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER_CN();
        if (pgmRtn) return;
        S000_CALL_BPZFSSVR();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_PARAMETER_CN() throws IOException,SQLException,Exception {
        BPCOFSVR.KEY.SVR_NO = BPB1110_AWA_1110.SVR_NO;
        BPCOFSVR.VAL.EFF_DATE = BPB1110_AWA_1110.EFF_DT;
        BPCOFSVR.VAL.EXP_DATE = BPB1110_AWA_1110.EXP_DT;
        BPCOFSVR.VAL.AUT_FLG = BPB1110_AWA_1110.AUT_FLG;
        BPCOFSVR.VAL.DRMCR_FLG = BPB1110_AWA_1110.DMCR_FLG;
        BPCOFSVR.VAL.MATCH_FLG = BPB1110_AWA_1110.MAT_FLG;
        CEP.TRC(SCCGWA, BPCOFSVR.VAL.DRMCR_FLG);
        CEP.TRC(SCCGWA, BPCOFSVR.VAL.MATCH_FLG);
        for (WS_CNT1 = 1; WS_CNT1 <= 20; WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, WS_CNT1);
            CEP.TRC(SCCGWA, BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].FEE_CODE);
            BPCOFSVR.VAL.DATA[WS_CNT1-1].FEE_CODE = BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].FEE_CODE;
            CEP.TRC(SCCGWA, BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].PRD_CODE);
            BPCOFSVR.VAL.DATA[WS_CNT1-1].PROD_CODE = BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].PRD_CODE;
            CEP.TRC(SCCGWA, BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].BVF_CODE);
            BPCOFSVR.VAL.DATA[WS_CNT1-1].BVF_CODE = BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].BVF_CODE;
            CEP.TRC(SCCGWA, BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].BNK_FLG);
            BPCOFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.BNK_FLG = BPB1110_AWA_1110.FEE_INFO[WS_CNT1-1].BNK_FLG;
