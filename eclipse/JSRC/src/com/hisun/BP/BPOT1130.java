package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1130 {
    String JIBS_tmp_str[] = new String[10];
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP057";
    String CPN_FAMO_MAINTAIN = "BP-F-S-MAINTAIN-FAMO";
    String CPN_CCY_INQUIRE = "BP-INQUIRE-CCY";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    BPOT1130_WS_DATE WS_DATE = new BPOT1130_WS_DATE();
    String WS_TXT = " ";
    String WS_QUEUE = " ";
    long WS_RESP = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFAMO BPCOFAMO = new BPCOFAMO();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPB1127_AWA_1127 BPB1127_AWA_1127;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1130 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1127_AWA_1127>");
        BPB1127_AWA_1127 = (BPB1127_AWA_1127) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCQCCY);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MODIFY_AMO_RATE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB1127_AWA_1127.AMORT_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMO_CODE_NOTINP;
            WS_FLD_NO = BPB1127_AWA_1127.AMORT_CD_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1127_AWA_1127.EFF_DATE);
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPB1127_AWA_1127.EFF_DATE;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR;
            WS_FLD_NO = BPB1127_AWA_1127.EFF_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB1127_AWA_1127.EXP_DATE);
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPB1127_AWA_1127.EXP_DATE;
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EXP_DATE_ERR;
            WS_FLD_NO = BPB1127_AWA_1127.EXP_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB1127_AWA_1127.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            CEP.TRC(SCCGWA, BPB1127_AWA_1127.EFF_DATE);
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_LT_ACDT;
            WS_FLD_NO = BPB1127_AWA_1127.EFF_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB1127_AWA_1127.EXP_DATE <= BPB1127_AWA_1127.EFF_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_GT_EXPDT;
            WS_FLD_NO = BPB1127_AWA_1127.EXP_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB1127_AWA_1127.AMO_MTH == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMO_MTH_NOTINPU;
            WS_FLD_NO = BPB1127_AWA_1127.AMO_MTH_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB1127_AWA_1127.AMO_MTH != '0' 
            && BPB1127_AWA_1127.AMO_MTH != '1' 
            && BPB1127_AWA_1127.AMO_MTH != '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMO_MTH_ERR;
            WS_FLD_NO = BPB1127_AWA_1127.AMO_MTH_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB1127_AWA_1127.AMO_TMS.equalsIgnoreCase("0") 
            && BPB1127_AWA_1127.AMO_MTH == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_TERM_NOTINPUT;
            WS_FLD_NO = BPB1127_AWA_1127.AMO_TMS_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB1127_AWA_1127.AMO_CYCL == ' ' 
            && BPB1127_AWA_1127.AMO_MTH == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMO_CYC_NOTINPU;
            WS_FLD_NO = BPB1127_AWA_1127.AMO_CYCL_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB1127_AWA_1127.AMO_CYCL != '0' 
            && BPB1127_AWA_1127.AMO_CYCL != '1' 
            && BPB1127_AWA_1127.AMO_CYCL != '2' 
            && BPB1127_AWA_1127.AMO_CYCL != '3' 
            && BPB1127_AWA_1127.AMO_MTH == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_CYC_INVALID;
            WS_FLD_NO = BPB1127_AWA_1127.AMO_CYCL_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB1127_AWA_1127.AMO_CNT.equalsIgnoreCase("0") 
            && BPB1127_AWA_1127.AMO_MTH == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMO_CNT_NOTINPU;
            WS_FLD_NO = BPB1127_AWA_1127.AMO_CNT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB1127_AWA_1127.MIN_AMT == 0 
            && BPB1127_AWA_1127.AMO_MTH == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_MIN_AMT_NOTINPU;
            WS_FLD_NO = BPB1127_AWA_1127.MIN_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB1127_AWA_1127.MAX_AMT == 0 
            && BPB1127_AWA_1127.AMO_MTH == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_MAX_AMT_NOTINPU;
            WS_FLD_NO = BPB1127_AWA_1127.MAX_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, "DATE");
        CEP.TRC(SCCGWA, BPB1127_AWA_1127.AMO_DATE);
        if (BPB1127_AWA_1127.AMO_CYCL == '2' 
            && BPB1127_AWA_1127.AMO_DATE != 1 
            && BPB1127_AWA_1127.AMO_DATE != 2 
            && BPB1127_AWA_1127.AMO_DATE != 3 
            && BPB1127_AWA_1127.AMO_DATE != 4 
            && BPB1127_AWA_1127.AMO_DATE != 5 
            && BPB1127_AWA_1127.AMO_DATE != 6 
            && BPB1127_AWA_1127.AMO_DATE != 7) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMO_DATE_ERR2;
            WS_FLD_NO = BPB1127_AWA_1127.AMO_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB1127_AWA_1127.AMO_CYCL == '1' 
            && BPB1127_AWA_1127.AMO_DATE != 1 
            && BPB1127_AWA_1127.AMO_DATE != 2 
            && BPB1127_AWA_1127.AMO_DATE != 3 
            && BPB1127_AWA_1127.AMO_DATE != 4 
            && BPB1127_AWA_1127.AMO_DATE != 5 
            && BPB1127_AWA_1127.AMO_DATE != 6 
            && BPB1127_AWA_1127.AMO_DATE != 7 
            && BPB1127_AWA_1127.AMO_DATE != 8 
            && BPB1127_AWA_1127.AMO_DATE != 9 
            && BPB1127_AWA_1127.AMO_DATE != 10 
            && BPB1127_AWA_1127.AMO_DATE != 11 
            && BPB1127_AWA_1127.AMO_DATE != 12 
            && BPB1127_AWA_1127.AMO_DATE != 13 
            && BPB1127_AWA_1127.AMO_DATE != 14 
            && BPB1127_AWA_1127.AMO_DATE != 15 
            && BPB1127_AWA_1127.AMO_DATE != 16 
            && BPB1127_AWA_1127.AMO_DATE != 17 
            && BPB1127_AWA_1127.AMO_DATE != 18 
            && BPB1127_AWA_1127.AMO_DATE != 19 
            && BPB1127_AWA_1127.AMO_DATE != 20 
            && BPB1127_AWA_1127.AMO_DATE != 21 
            && BPB1127_AWA_1127.AMO_DATE != 22 
            && BPB1127_AWA_1127.AMO_DATE != 23 
            && BPB1127_AWA_1127.AMO_DATE != 24 
            && BPB1127_AWA_1127.AMO_DATE != 25 
            && BPB1127_AWA_1127.AMO_DATE != 26 
            && BPB1127_AWA_1127.AMO_DATE != 27 
            && BPB1127_AWA_1127.AMO_DATE != 28 
            && BPB1127_AWA_1127.AMO_DATE != 29 
            && BPB1127_AWA_1127.AMO_DATE != 30 
            && BPB1127_AWA_1127.AMO_DATE != 31) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMO_DATE_ERR1;
            WS_FLD_NO = BPB1127_AWA_1127.AMO_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB1127_AWA_1127.AMO_CYCL == '0') {
            IBS.init(SCCGWA, SCCCKDT);
            IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.TR_DATE+"", WS_DATE);
            IBS.CPY2CLS(SCCGWA, BPB1127_AWA_1127.AMO_DATE+"", WS_DATE.WS_MMDD);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DATE);
            SCCCKDT.DATE = Integer.parseInt(JIBS_tmp_str[0]);
            SCSSCKDT SCSSCKDT3 = new SCSSCKDT();
            SCSSCKDT3.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMO_DATE_ERR0;
                WS_FLD_NO = BPB1127_AWA_1127.AMO_DATE_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (BPB1127_AWA_1127.AMO_MTH == '1') {
            R000_CCY_CHECK();
        }
        if (BPB1127_AWA_1127.MAX_AMT < BPB1127_AWA_1127.MIN_AMT 
            && BPB1127_AWA_1127.MAX_AMT != 0 
            && BPB1127_AWA_1127.MIN_AMT != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMT_ERR;
            WS_FLD_NO = BPB1127_AWA_1127.MAX_AMT_NO;
            WS_FLD_NO = BPB1127_AWA_1127.MIN_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        R000_CHECK_RESULT_PROC();
    }
    public void B020_MODIFY_AMO_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFAMO);
        BPCOFAMO.FUNC = 'U';
        BPCOFAMO.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFSAMO();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOFAMO.KEY.AMORT_CODE = BPB1127_AWA_1127.AMORT_CD;
        BPCOFAMO.DATE.EFF_DATE = BPB1127_AWA_1127.EFF_DATE;
        BPCOFAMO.DATE.EXP_DATE = BPB1127_AWA_1127.EXP_DATE;
        BPCOFAMO.VAL.AMO_DESC = BPB1127_AWA_1127.AMO_DESC;
        BPCOFAMO.VAL.AMO_MTH = BPB1127_AWA_1127.AMO_MTH;
        BPCOFAMO.VAL.AMO_TMS = BPB1127_AWA_1127.AMO_TMS;
        BPCOFAMO.VAL.AMO_CYCLE = BPB1127_AWA_1127.AMO_CYCL;
        BPCOFAMO.VAL.AMO_CNT = BPB1127_AWA_1127.AMO_CNT;
        BPCOFAMO.VAL.AMO_DATE = BPB1127_AWA_1127.AMO_DATE;
        BPCOFAMO.VAL.AMO_CCY = BPB1127_AWA_1127.AMO_CCY;
        BPCOFAMO.VAL.MAX_AMT = BPB1127_AWA_1127.MAX_AMT;
        BPCOFAMO.VAL.MIN_AMT = BPB1127_AWA_1127.MIN_AMT;
        BPCOFAMO.VAL.BACK_FLG = BPB1127_AWA_1127.BACK_FLG;
        BPCOFAMO.VAL.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOFAMO.VAL.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
    }
    public void R000_CCY_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = BPB1127_AWA_1127.AMO_CCY;
        IBS.CALLCPN(SCCGWA, CPN_CCY_INQUIRE, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_FLD_NO = BPB1127_AWA_1127.AMO_CCY_NO;
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_BPZFSAMO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_FAMO_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCOFAMO;
        SCCCALL.ERR_FLDNO = BPB1127_AWA_1127.AMORT_CD;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void R000_CHECK_RESULT_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.TRC(SCCGWA, "ERROR");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
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
