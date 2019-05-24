package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6214 {
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_MAINTAIN_CITY_CODE = "BP-S-MAIN-CITY";
    String CPN_BP_I_INQ_CNTY = "BP-I-INQ-CNTY ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSMCIT BPCSMCIT = new BPCSMCIT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCIQCNT BPCIQCNT = new BPCIQCNT();
    SCCGWA SCCGWA;
    BPB6210_AWA_6210 BPB6210_AWA_6210;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT6214 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6210_AWA_6210>");
        BPB6210_AWA_6210 = (BPB6210_AWA_6210) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_ADD_CITY_CODE();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB6210_AWA_6210.CNTY_CD);
        CEP.TRC(SCCGWA, BPB6210_AWA_6210.CITY_CD);
        CEP.TRC(SCCGWA, BPB6210_AWA_6210.CITY_ENM);
        CEP.TRC(SCCGWA, BPB6210_AWA_6210.CITY_CNM);
        CEP.TRC(SCCGWA, BPB6210_AWA_6210.EFF_DT);
        CEP.TRC(SCCGWA, BPB6210_AWA_6210.EXP_DT);
        CEP.TRC(SCCGWA, BPB6210_AWA_6210.PDAYS);
        CEP.TRC(SCCGWA, BPB6210_AWA_6210.PCUT_TM);
        CEP.TRC(SCCGWA, BPB6210_AWA_6210.CCUT_TM);
        CEP.TRC(SCCGWA, BPB6210_AWA_6210.CAL_CD);
        CEP.TRC(SCCGWA, BPB6210_AWA_6210.TM_ZONE);
        if (BPB6210_AWA_6210.CNTY_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNTY_CD_M_INPUT;
            WS_FLD_NO = BPB6210_AWA_6210.CNTY_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCIQCNT);
            BPCIQCNT.INPUT_DAT.CNTY_CD = BPB6210_AWA_6210.CNTY_CD;
            S000_CALL_BPZIQCNT();
            if (pgmRtn) return;
            if (BPCIQCNT.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNTY_CD_NOTFND;
                WS_FLD_NO = BPB6210_AWA_6210.CNTY_CD_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (BPB6210_AWA_6210.CITY_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CITY_CD_M_INPUT;
            WS_FLD_NO = BPB6210_AWA_6210.CITY_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.AC_DATE > BPB6210_AWA_6210.EFF_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DT_INVALID;
            WS_FLD_NO = BPB6210_AWA_6210.EFF_DT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB6210_AWA_6210.EFF_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DT_M_INPUT;
            WS_FLD_NO = BPB6210_AWA_6210.EFF_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB6210_AWA_6210.EFF_DT;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DT_INVALID;
                WS_FLD_NO = BPB6210_AWA_6210.EFF_DT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (BPB6210_AWA_6210.EXP_DT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB6210_AWA_6210.EXP_DT;
            SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
            SCSSCKDT2.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXP_DT_INVALID;
                WS_FLD_NO = BPB6210_AWA_6210.EXP_DT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            } else {
                if (BPB6210_AWA_6210.EFF_DT >= BPB6210_AWA_6210.EXP_DT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DT_GT_EXP_DT;
                    WS_FLD_NO = BPB6210_AWA_6210.EFF_DT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
                if (BPB6210_AWA_6210.EXP_DT <= SCCGWA.COMM_AREA.AC_DATE) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXP_DT_INVALID;
                    WS_FLD_NO = BPB6210_AWA_6210.EXP_DT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            }
        }
        if (BPB6210_AWA_6210.PDAYS == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PDAYS_M_INPUT;
            WS_FLD_NO = BPB6210_AWA_6210.PDAYS_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BPB6210_AWA_6210.CAL_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CALR_CD_M_INPUT;
            WS_FLD_NO = BPB6210_AWA_6210.CAL_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        } else {
        }
        if (BPB6210_AWA_6210.TM_ZONE.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TIME_ZONE_M_INPUT;
            WS_FLD_NO = BPB6210_AWA_6210.TM_ZONE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        R000_CHECK_RESULT_PROC();
        if (pgmRtn) return;
    }
    public void B020_ADD_CITY_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMCIT);
        BPCSMCIT.CNTY_CODE = BPB6210_AWA_6210.CNTY_CD;
        BPCSMCIT.CITY_CODE = BPB6210_AWA_6210.CITY_CD;
        BPCSMCIT.ENG_NAME = BPB6210_AWA_6210.CITY_ENM;
        BPCSMCIT.LOA_NAME = BPB6210_AWA_6210.CITY_CNM;
        BPCSMCIT.EFF_DT = BPB6210_AWA_6210.EFF_DT;
        BPCSMCIT.EXP_DT = BPB6210_AWA_6210.EXP_DT;
        BPCSMCIT.PDAYS = BPB6210_AWA_6210.PDAYS;
        BPCSMCIT.P_CUTTIME = BPB6210_AWA_6210.PCUT_TM;
        BPCSMCIT.C_CUTTIME = BPB6210_AWA_6210.CCUT_TM;
        BPCSMCIT.CALR_CODE = BPB6210_AWA_6210.CAL_CD;
        BPCSMCIT.TIMEZONE = BPB6210_AWA_6210.TM_ZONE;
        BPCSMCIT.FUNC = 'A';
        S000_CALL_BPZSMCIT();
        if (pgmRtn) return;
    }
    public void R000_CHECK_RESULT_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSMCIT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAIN-CITY", BPCSMCIT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_CALL_BPZIQCNT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-I-INQ-CNTY ", BPCIQCNT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
