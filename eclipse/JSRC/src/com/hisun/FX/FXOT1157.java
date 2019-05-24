package com.hisun.FX;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class FXOT1157 {
    String JIBS_tmp_str[] = new String[10];
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    FXOT1157_WS_STR_DT WS_STR_DT = new FXOT1157_WS_STR_DT();
    FXOT1157_WS_END_DT WS_END_DT = new FXOT1157_WS_END_DT();
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    FXCSTHIS FXCSTHIS = new FXCSTHIS();
    FXRDIRFX FXRDIRFX = new FXRDIRFX();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICQACRI CICQACRI = new CICQACRI();
    SCCGWA SCCGWA;
    FXB1157_AWA_1157 FXB1157_AWA_1157;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FXOT1157 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "FXB1157_AWA_1157>");
        FXB1157_AWA_1157 = (FXB1157_AWA_1157) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_CHECK_INPUT();
        B010_BRW_REC_PROC();
    }
    public void B020_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXB1157_AWA_1157.CTA_YR);
        if (FXB1157_AWA_1157.CTA_YR == ' ' 
            || FXB1157_AWA_1157.CTA_YR == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_ERR_MUST_INPUT);
        } else {
            if (!IBS.isNumeric(FXB1157_AWA_1157.CTA_YR+"")) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_YR_INVALID);
            }
        }
        CEP.TRC(SCCGWA, FXB1157_AWA_1157.CTA_NO);
        if (FXB1157_AWA_1157.CTA_NO.trim().length() == 0 
            || FXB1157_AWA_1157.CTA_NO.equalsIgnoreCase("0")) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_ERR_MUST_INPUT);
        }
    }
    public void B010_BRW_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXCSTHIS);
        FXCSTHIS.FUNC = 'B';
        WS_STR_DT.WS_STR_YR = FXB1157_AWA_1157.CTA_YR;
        WS_END_DT.WS_END_YR = FXB1157_AWA_1157.CTA_YR;
        WS_STR_DT.WS_STR_MMDD = 101;
        WS_END_DT.WS_END_MMDD = 1231;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_STR_DT);
        FXCSTHIS.STR_DT = Integer.parseInt(JIBS_tmp_str[0]);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_END_DT);
        FXCSTHIS.END_DT = Integer.parseInt(JIBS_tmp_str[0]);
        FXCSTHIS.CTA_NO = FXB1157_AWA_1157.CTA_NO;
        S000_CALL_FXZSTHIS();
    }
    public void S000_CALL_FXZSTHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FX-S-MAIN-THIS", FXCSTHIS);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
