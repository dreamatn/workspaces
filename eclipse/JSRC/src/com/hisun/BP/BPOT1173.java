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
import com.hisun.SC.SCCPRMR;

public class BPOT1173 {
    boolean pgmRtn = false;
    char ERROR = 'E';
    String OUTPUT_FMT = "BP067";
    String SCSSCKDT = "SCSSCKDT";
    String FSVR_MAINTAIN = "BP-F-S-MAINTAIN-FSVR";
    String F_T_FEE_INFO = "BP-F-T-FEE-INFO";
    String F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY";
    BPOT1173_WS_VARIABLES WS_VARIABLES = new BPOT1173_WS_VARIABLES();
    BPOT1173_WS_TRT_VAR WS_TRT_VAR = new BPOT1173_WS_TRT_VAR();
    BPOT1173_WS_COND_FLG WS_COND_FLG = new BPOT1173_WS_COND_FLG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFSVR BPCOFSVR = new BPCOFSVR();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCPRMR SCCPRMR = new SCCPRMR();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    SCCGWA SCCGWA;
    BPB1110_AWA_1110 AWA_1110;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1173 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AWA_1110 = new BPB1110_AWA_1110();
        IBS.init(SCCGWA, AWA_1110);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWA_AREA_PTR, AWA_1110);
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_CN();
            if (pgmRtn) return;
            B020_CREATE_SVR_FEE_CN();
            if (pgmRtn) return;
        } else {
            B020_CREATE_SVR_FEE();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        if (AWA_1110.SVR_NO.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_AWA_SVR_NO_ERR;
            WS_VARIABLES.FLD_NO = AWA_1110.SVR_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCPRMR);
            IBS.init(SCCGWA, WS_TRT_VAR);
            WS_TRT_VAR.WS_KEY.TYP = "TRT";
            WS_TRT_VAR.WS_KEY.CD = AWA_1110.SVR_NO;
            SCCPRMR.DAT_PTR = WS_TRT_VAR;
            S000_CALL_SCZPRMR();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.BP_PARM_NOTFND)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_SVR_NO_NOTFOUND;
                WS_VARIABLES.FLD_NO = AWA_1110.SVR_NO_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = AWA_1110.EFF_DT;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_FEE_EFF_DATE_ERR;
            WS_VARIABLES.FLD_NO = AWA_1110.EFF_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = AWA_1110.EXP_DT;
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_FEE_EXP_DATE_ERR;
            WS_VARIABLES.FLD_NO = AWA_1110.EXP_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (AWA_1110.EFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_FEE_EFFDT_LT_ACDT;
            WS_VARIABLES.FLD_NO = AWA_1110.EFF_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (AWA_1110.EXP_DT <= AWA_1110.EFF_DT) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_FEE_EFFDT_GT_EXPDT;
            WS_VARIABLES.FLD_NO = AWA_1110.EXP_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (AWA_1110.AUT_FLG != '1' 
            && AWA_1110.AUT_FLG != '0') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_AWA_AUT_FLG_ERR;
            WS_VARIABLES.FLD_NO = AWA_1110.AUT_FLG_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AWA_1110.SVR_NO);
        CEP.TRC(SCCGWA, AWA_1110.EFF_DT);
        CEP.TRC(SCCGWA, AWA_1110.EXP_DT);
        CEP.TRC(SCCGWA, AWA_1110.AUT_FLG);
        CEP.TRC(SCCGWA, AWA_1110.FEE_INFO[1-1].FEE_CODE);
        if (AWA_1110.FEE_INFO[1-1].FEE_CODE.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_AWA_FEE_FIRST;
            WS_VARIABLES.FLD_NO = AWA_1110.FEE_INFO[1-1].FEE_CODE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_VARIABLES.FEE_NEXT = 1;
        for (WS_VARIABLES.FEE_NO = 2; WS_VARIABLES.FEE_NO < 20; WS_VARIABLES.FEE_NO += 1) {
            WS_VARIABLES.FEE_NEXT = (short) (WS_VARIABLES.FEE_NO + 1);
            if (AWA_1110.FEE_INFO[WS_VARIABLES.FEE_NO-1].FEE_CODE.trim().length() == 0 
                && AWA_1110.FEE_INFO[WS_VARIABLES.FEE_NEXT-1].FEE_CODE.trim().length() > 0) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_AWA_FEE_INFO;
                WS_VARIABLES.FLD_NO = AWA_1110.FEE_INFO[WS_VARIABLES.FEE_NEXT-1].FEE_CODE_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        for (WS_VARIABLES.CNT1 = 1; WS_VARIABLES.CNT1 < 20 
            && AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].FEE_CODE.trim().length() != 0; WS_VARIABLES.CNT1 += 1) {
            IBS.init(SCCGWA, BPRFBAS);
            IBS.init(SCCGWA, BPCTFBAS);
            BPRFBAS.KEY.FEE_CODE = AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].FEE_CODE;
            BPCTFBAS.INFO.FUNC = 'Q';
            BPCTFBAS.INFO.POINTER = BPRFBAS;
            BPCTFBAS.INFO.REC_LEN = 312;
            S000_CALL_BPZTFBAS();
            if (pgmRtn) return;
            if (AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].PRD_CODE.trim().length() > 0) {
                IBS.init(SCCGWA, BPCPQPRD);
                BPCPQPRD.PRDT_CODE = AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].PRD_CODE;
                S000_CALL_BPZPQPRD();
                if (pgmRtn) return;
            }
            if (AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].BNK_FLG != '0' 
                && AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].BNK_FLG != '1' 
                && AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].BNK_FLG != ' ') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_AWA_BNK_FLG_ERR;
                WS_VARIABLES.FLD_NO = AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].BNK_FLG_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].CITY_FLG != '0' 
                && AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].CITY_FLG != '1' 
                && AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].CITY_FLG != ' ') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_AWA_CITY_FLG_ERR;
                WS_VARIABLES.FLD_NO = AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].CITY_FLG_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].CI_FLG != '1' 
                && AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].CI_FLG != '2' 
                && AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].CI_FLG != '3' 
                && AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].CI_FLG != ' ') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_AWA_CI_FLG_ERR;
                WS_VARIABLES.FLD_NO = AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].CI_FLG_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].BV_FLG != '1' 
                && AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].BV_FLG != '2' 
                && AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].BV_FLG != '3' 
                && AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].BV_FLG != '4' 
                && AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].BV_FLG != '6' 
                && AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].BV_FLG != ' ') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_AWA_BV_FLG_ERR;
                WS_VARIABLES.FLD_NO = AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].BV_FLG_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].BVF_CODE.trim().length() > 0) {
                IBS.init(SCCGWA, BPCFBVQU);
                BPCFBVQU.TX_DATA.KEY.CODE = AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].BVF_CODE;
                S000_CALL_BPZFBVQU();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].DC_FLG);
            if (AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].DC_FLG != '0' 
                && AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].DC_FLG != '1' 
                && AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].DC_FLG != ' ') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_AWA_DC_FLG_ERR;
                WS_VARIABLES.FLD_NO = AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].DC_FLG_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].DEF_FLG);
            if (AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].DEF_FLG.trim().length() > 0 
                && !AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].DEF_FLG.equalsIgnoreCase("00") 
                && !AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].DEF_FLG.equalsIgnoreCase("01") 
                && !AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].DEF_FLG.equalsIgnoreCase("02") 
                && !AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].DEF_FLG.equalsIgnoreCase("03") 
                && !AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].DEF_FLG.equalsIgnoreCase("04") 
                && !AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].DEF_FLG.equalsIgnoreCase("05")) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_AWA_DEF_FLG_ERR;
                WS_VARIABLES.FLD_NO = AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].DEF_FLG_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (AWA_1110.DMCR_FLG == ' ') {
            AWA_1110.DMCR_FLG = 'N';
        }
        CEP.TRC(SCCGWA, AWA_1110.MAT_FLG);
        if (AWA_1110.MAT_FLG == ' ') {
            AWA_1110.MAT_FLG = 'Y';
        }
    }
    public void B020_CREATE_SVR_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFSVR);
        BPCOFSVR.FUNC = 'A';
        BPCOFSVR.OUTPUT_FMT = OUTPUT_FMT;
        S000_CALL_BPZFSSVR();
        if (pgmRtn) return;
    }
    public void B020_CREATE_SVR_FEE_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFSVR);
        BPCOFSVR.FUNC = 'A';
        BPCOFSVR.OUTPUT_FMT = OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER_CN();
        if (pgmRtn) return;
        S000_CALL_BPZFSSVR();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_PARAMETER_CN() throws IOException,SQLException,Exception {
        BPCOFSVR.KEY.SVR_NO = AWA_1110.SVR_NO;
        BPCOFSVR.VAL.EFF_DATE = AWA_1110.EFF_DT;
        BPCOFSVR.VAL.EXP_DATE = AWA_1110.EXP_DT;
        BPCOFSVR.VAL.AUT_FLG = AWA_1110.AUT_FLG;
        BPCOFSVR.VAL.DRMCR_FLG = AWA_1110.DMCR_FLG;
        BPCOFSVR.VAL.MATCH_FLG = AWA_1110.MAT_FLG;
        for (WS_VARIABLES.CNT1 = 1; WS_VARIABLES.CNT1 <= 20; WS_VARIABLES.CNT1 += 1) {
            CEP.TRC(SCCGWA, WS_VARIABLES.CNT1);
            CEP.TRC(SCCGWA, AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].FEE_CODE);
            BPCOFSVR.VAL.DATA[WS_VARIABLES.CNT1-1].FEE_CODE = AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].FEE_CODE;
            CEP.TRC(SCCGWA, AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].PRD_CODE);
            BPCOFSVR.VAL.DATA[WS_VARIABLES.CNT1-1].PROD_CODE = AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].PRD_CODE;
            CEP.TRC(SCCGWA, AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].BVF_CODE);
            BPCOFSVR.VAL.DATA[WS_VARIABLES.CNT1-1].BVF_CODE = AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].BVF_CODE;
            CEP.TRC(SCCGWA, AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].BNK_FLG);
            BPCOFSVR.VAL.DATA[WS_VARIABLES.CNT1-1].ATTR_VAL.BNK_FLG = AWA_1110.FEE_INFO[WS_VARIABLES.CNT1-1].BNK_FLG;
