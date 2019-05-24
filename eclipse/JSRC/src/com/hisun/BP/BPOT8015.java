package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT8015 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    char K_ERROR = 'E';
    BPOT8015_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT8015_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCSTYPH BPCSTYPH = new BPCSTYPH();
    BPCPQBCH BPCPQBCH = new BPCPQBCH();
    SCCGWA SCCGWA;
    BPB8000_AWA_8000 BPB8000_AWA_8000;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT8015 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB8000_AWA_8000>");
        BPB8000_AWA_8000 = (BPB8000_AWA_8000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B100_READ_PROCESS();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB8000_AWA_8000.UNSCH_TY);
        CEP.TRC(SCCGWA, BPB8000_AWA_8000.REST_COD);
        CEP.TRC(SCCGWA, BPB8000_AWA_8000.EXCH_FLG);
        if (BPB8000_AWA_8000.FUNC != 'S' 
            && BPB8000_AWA_8000.FUNC != 'C') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB8000_AWA_8000.FUNC_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB8000_AWA_8000.FUNC == 'S') {
            if (BPB8000_AWA_8000.UNSCH_TY.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_UNSCH_TYPE, WS_TEMP_VARIABLE.WS_MSGID);
                WS_TEMP_VARIABLE.WS_FLD_NO = BPB8000_AWA_8000.UNSCH_TY_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB8000_AWA_8000.REST_COD == ' ') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_REST_CODE, WS_TEMP_VARIABLE.WS_MSGID);
                WS_TEMP_VARIABLE.WS_FLD_NO = BPB8000_AWA_8000.REST_COD_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB8000_AWA_8000.EXCH_FLG == ' ') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_EXCH_FLG, WS_TEMP_VARIABLE.WS_MSGID);
                WS_TEMP_VARIABLE.WS_FLD_NO = BPB8000_AWA_8000.EXCH_FLG_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB8000_AWA_8000.REST_COD == '1' 
                && BPB8000_AWA_8000.EXCH_FLG != 'N') {
                WS_TEMP_VARIABLE.WS_FLD_NO = BPB8000_AWA_8000.EXCH_FLG_NO;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, BPCPQBCH);
        BPCPQBCH.ACCT = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPCPQBCH();
        CEP.TRC(SCCGWA, BPCPQBCH.UNSCH_HOL);
        if (BPCPQBCH.UNSCH_HOL == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_SETTING_NOT_ALLOWED, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTYPH);
        BPCSTYPH.FUNC = BPB8000_AWA_8000.FUNC;
        BPCSTYPH.REST_CODE = "" + BPB8000_AWA_8000.REST_COD;
        JIBS_tmp_int = BPCSTYPH.REST_CODE.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) BPCSTYPH.REST_CODE = "0" + BPCSTYPH.REST_CODE;
        CEP.TRC(SCCGWA, BPCSTYPH.REST_CODE);
        BPCSTYPH.UNSCH_TYP = BPB8000_AWA_8000.UNSCH_TY;
        BPCSTYPH.EXCH_FLG = "" + BPB8000_AWA_8000.EXCH_FLG;
        JIBS_tmp_int = BPCSTYPH.EXCH_FLG.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) BPCSTYPH.EXCH_FLG = "0" + BPCSTYPH.EXCH_FLG;
        BPCSTYPH.CITY_CD = BPB8000_AWA_8000.CITY_CD;
        CEP.TRC(SCCGWA, BPCSTYPH.FUNC);
        S000_CALL_BPZSTYPH();
    }
    public void S000_CALL_BPCPQBCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-BCH", BPCPQBCH);
    }
    public void S000_CALL_BPZSTYPH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-SET-TYPH-STS     ", BPCSTYPH);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
