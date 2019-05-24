package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6213 {
    char K_ERROR = 'E';
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_MAINTAIN_CITY_CODE = "BP-S-MAIN-CITY";
    String CPN_BP_I_INQ_CNTY = "BP-I-INQ-CNTY ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_FUNC_FLG = ' ';
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
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6213 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6210_AWA_6210>");
        BPB6210_AWA_6210 = (BPB6210_AWA_6210) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_DELETE_CITY_CODE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB6210_AWA_6210.CNTY_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNTY_CD_M_INPUT;
            WS_FLD_NO = BPB6210_AWA_6210.CNTY_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        } else {
            IBS.init(SCCGWA, BPCIQCNT);
            BPCIQCNT.INPUT_DAT.CNTY_CD = BPB6210_AWA_6210.CNTY_CD;
            S000_CALL_BPZIQCNT();
            if (BPCIQCNT.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNTY_CD_NOTFND;
                WS_FLD_NO = BPB6210_AWA_6210.CNTY_CD_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (BPB6210_AWA_6210.CITY_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CITY_CD_M_INPUT;
            WS_FLD_NO = BPB6210_AWA_6210.CITY_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB6210_AWA_6210.EFF_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DT_M_INPUT;
            WS_FLD_NO = BPB6210_AWA_6210.EFF_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        } else {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB6210_AWA_6210.EFF_DT;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DT_INVALID;
                WS_FLD_NO = BPB6210_AWA_6210.EFF_DT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        R000_CHECK_RESULT_PROC();
    }
    public void B030_DELETE_CITY_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMCIT);
        BPCSMCIT.CNTY_CODE = BPB6210_AWA_6210.CNTY_CD;
        BPCSMCIT.CITY_CODE = BPB6210_AWA_6210.CITY_CD;
        BPCSMCIT.EFF_DT = BPB6210_AWA_6210.EFF_DT;
        BPCSMCIT.FUNC = 'D';
        S000_CALL_BPZSMCIT();
    }
    public void R000_CHECK_RESULT_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSMCIT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAIN-CITY", BPCSMCIT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_CALL_BPZIQCNT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-I-INQ-CNTY ", BPCIQCNT);
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
