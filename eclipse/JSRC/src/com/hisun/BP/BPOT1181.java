package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1181 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP068";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String CPN_SEXP_MAINTAIN = "BP-F-S-MAINTAIN-FEXP";
    String CPN_INQUIRE_BPZQCCY = "BP-INQUIRE-CCY      ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    short WS_FEE_NO = 0;
    short WS_FEE_NEXT = 0;
    String WS_TXT = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFEXP BPCOFEXP = new BPCOFEXP();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    BPB1180_AWA_1180 BPB1180_AWA_1180;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1181 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1180_AWA_1180>");
        BPB1180_AWA_1180 = (BPB1180_AWA_1180) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CREATE_EXP_RATE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((BPB1180_AWA_1180.DER_CODE.trim().length() == 0)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_DER_CODE_ERR;
            WS_FLD_NO = BPB1180_AWA_1180.DER_CODE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((BPB1180_AWA_1180.DER_RES.trim().length() == 0)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_DER_RESC_ERR;
            WS_FLD_NO = BPB1180_AWA_1180.DER_RES_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPB1180_AWA_1180.EFF_DATE;
        CEP.TRC(SCCGWA, SCCCKDT.DATE);
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR;
            WS_FLD_NO = BPB1180_AWA_1180.EFF_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPB1180_AWA_1180.EXP_DATE;
        CEP.TRC(SCCGWA, SCCCKDT.DATE);
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, SCCCKDT.RC);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EXP_DATE_ERR;
            WS_FLD_NO = BPB1180_AWA_1180.EXP_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB1180_AWA_1180.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_LT_ACDT;
            WS_FLD_NO = BPB1180_AWA_1180.EFF_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB1180_AWA_1180.EXP_DATE <= BPB1180_AWA_1180.EFF_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_GT_EXPDT;
            WS_FLD_NO = BPB1180_AWA_1180.EXP_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((BPB1180_AWA_1180.DER_INFO[1-1].FEE_CODE.trim().length() == 0)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_FEE_FIRST;
            WS_FLD_NO = BPB1180_AWA_1180.DER_INFO[1-1].FEE_CODE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_FEE_NEXT = 1;
        WS_FEE_NO = 1;
        for (WS_FEE_NO = 1; WS_FEE_NO < 50; WS_FEE_NO += 1) {
            WS_FEE_NEXT = (short) (WS_FEE_NO + 1);
            if (BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].FEE_CODE.trim().length() == 0 
                && BPB1180_AWA_1180.DER_INFO[WS_FEE_NEXT-1].FEE_CODE.trim().length() > 0) {
                CEP.TRC(SCCGWA, WS_FEE_NO);
                WS_TXT = BPB1180_AWA_1180.DER_INFO[WS_FEE_NEXT-1].FEE_CODE;
                CEP.TRC(SCCGWA, WS_TXT);
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_FEE_INFO;
                WS_FLD_NO = BPB1180_AWA_1180.DER_INFO[WS_FEE_NEXT-1].FEE_CODE_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].FEE_CODE.trim().length() > 0 
                && BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].DER_FLG != '0' 
                && BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].DER_FLG != '1') {
                CEP.TRC(SCCGWA, WS_FEE_NO);
                WS_TXT = BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].FEE_CODE;
                CEP.TRC(SCCGWA, WS_TXT);
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_DER_FLG_ERR;
                WS_FLD_NO = BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].DER_FLG_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].FEE_CODE.trim().length() > 0) {
                if (BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].CCY.trim().length() == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REF_CCY_MUST_INPUT;
                    WS_FLD_NO = BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].CCY_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                BPCQCCY.DATA.CCY = BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].CCY;
                IBS.CALLCPN(SCCGWA, CPN_INQUIRE_BPZQCCY, BPCQCCY);
                if (BPCQCCY.RC.RTNCODE != 0) {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                    WS_FLD_NO = BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].CCY_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].MAX_PERC != 0 
                    && BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].MAX_AMT != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MAX_PERC_AMT_ERR;
                    WS_FLD_NO = BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].MAX_PERC_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].MAX_PERC == 0 
                    && BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].MAX_AMT == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MAX_PERC_AMT_ERR;
                    WS_FLD_NO = BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].MAX_PERC_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].MAX_PERC != 0 
                    && BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].MAX_AMT == 0) {
                    if (BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].MAX_PERC > 100) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_MAX_PERC_ERR;
                        WS_FLD_NO = BPB1180_AWA_1180.DER_INFO[WS_FEE_NO-1].MAX_PERC_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                }
            }
        }
        R000_CHECK_ERROR();
    }
    public void B020_CREATE_EXP_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFEXP);
        BPCOFEXP.FUNC = 'A';
        BPCOFEXP.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFSEXP();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOFEXP.KEY.DER_CODE = BPB1180_AWA_1180.DER_CODE;
        BPCOFEXP.VAL.DER_DESC = BPB1180_AWA_1180.DER_RES;
        BPCOFEXP.VAL.EFF_DATE = BPB1180_AWA_1180.EFF_DATE;
        BPCOFEXP.VAL.EXP_DATE = BPB1180_AWA_1180.EXP_DATE;
        WS_CNT1 = 1;
        for (WS_CNT1 = 1; WS_CNT1 <= 50; WS_CNT1 += 1) {
            BPCOFEXP.VAL.EXP_DATA[WS_CNT1-1].FEE_CODE = BPB1180_AWA_1180.DER_INFO[WS_CNT1-1].FEE_CODE;
            BPCOFEXP.VAL.EXP_DATA[WS_CNT1-1].CCY = BPB1180_AWA_1180.DER_INFO[WS_CNT1-1].CCY;
            BPCOFEXP.VAL.EXP_DATA[WS_CNT1-1].DER_FLG = BPB1180_AWA_1180.DER_INFO[WS_CNT1-1].DER_FLG;
            BPCOFEXP.VAL.EXP_DATA[WS_CNT1-1].MAX_PERCENT = BPB1180_AWA_1180.DER_INFO[WS_CNT1-1].MAX_PERC;
            BPCOFEXP.VAL.EXP_DATA[WS_CNT1-1].MAX_AMT = BPB1180_AWA_1180.DER_INFO[WS_CNT1-1].MAX_AMT;
            BPCOFEXP.VAL.EXP_DATA[WS_CNT1-1].AUTH_LVL = BPB1180_AWA_1180.DER_INFO[WS_CNT1-1].AUTH_LVL;
        }
        BPCOFEXP.VAL.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOFEXP.VAL.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_CALL_BPZFSEXP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_SEXP_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCOFEXP;
        SCCCALL.ERR_FLDNO = BPB1180_AWA_1180.DER_CODE;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void R000_CHECK_ERROR() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
