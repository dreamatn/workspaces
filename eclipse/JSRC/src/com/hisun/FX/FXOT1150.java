package com.hisun.FX;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class FXOT1150 {
    DBParm FXTDIRFX_RD;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    FXCSDRFX FXCSDRFX = new FXCSDRFX();
    FXRDIRFX FXRDIRFX = new FXRDIRFX();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICQACRI CICQACRI = new CICQACRI();
    SCCGWA SCCGWA;
    FXB1150_AWA_1150 FXB1150_AWA_1150;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FXOT1150 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "FXB1150_AWA_1150>");
        FXB1150_AWA_1150 = (FXB1150_AWA_1150) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_CHECK_INPUT();
        B010_BRW_REC_PROC();
    }
    public void B020_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXB1150_AWA_1150.ORDER_BR);
        if (FXB1150_AWA_1150.ORDER_BR != ' ') {
            C000_CHECK_BR();
        } else {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_MUST_INPUT_BR);
        }
        CEP.TRC(SCCGWA, FXB1150_AWA_1150.REG_CD);
        if (FXB1150_AWA_1150.REG_CD.trim().length() > 0) {
            C010_CHECK_REG_CD();
        }
        CEP.TRC(SCCGWA, FXB1150_AWA_1150.CI_NO);
        CEP.TRC(SCCGWA, FXB1150_AWA_1150.TRA_AC);
        if (FXB1150_AWA_1150.TRA_AC.trim().length() > 0) {
            C030_CHECK_TRA_AC();
        }
        if (FXB1150_AWA_1150.TRN_DT != 0 
            && FXB1150_AWA_1150.TRN_E_DT != 0) {
            if (FXB1150_AWA_1150.TRN_DT > FXB1150_AWA_1150.TRN_E_DT) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_DT_ERROR);
            }
        }
        C040_CHECK_SUB_BR();
    }
    public void C000_CHECK_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = FXB1150_AWA_1150.ORDER_BR;
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_REC_NOTFND);
        }
        if (BPCPQORG.FX_BUSI.equalsIgnoreCase("00")) {
        }
    }
    public void C010_CHECK_REG_CD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXRDIRFX);
        FXRDIRFX.ES_REG_CD = FXB1150_AWA_1150.REG_CD;
        FXTDIRFX_RD = new DBParm();
        FXTDIRFX_RD.TableName = "FXTDIRFX";
        FXTDIRFX_RD.where = "ES_REG_CD = :FXRDIRFX.ES_REG_CD";
        IBS.READ(SCCGWA, FXRDIRFX, this, FXTDIRFX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_REC_NOTFND);
        }
    }
    public void C030_CHECK_TRA_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = FXB1150_AWA_1150.TRA_AC;
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI, true);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_REC_NOTFND);
        }
    }
    public void C040_CHECK_SUB_BR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        IBS.init(SCCGWA, BPCPRGST);
        BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPRGST.BR2 = FXB1150_AWA_1150.ORDER_BR;
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != FXB1150_AWA_1150.ORDER_BR) {
            S000_CALL_BPZPRGST();
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPRGST.RC);
        }
    }
    public void B010_BRW_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXCSDRFX);
        FXCSDRFX.FUNC = 'B';
        CEP.TRC(SCCGWA, FXB1150_AWA_1150.ORDER_BR);
        CEP.TRC(SCCGWA, FXB1150_AWA_1150.PROD_CD);
        CEP.TRC(SCCGWA, FXB1150_AWA_1150.REG_CD);
        CEP.TRC(SCCGWA, FXB1150_AWA_1150.CI_NO);
        CEP.TRC(SCCGWA, FXB1150_AWA_1150.TRA_AC);
        CEP.TRC(SCCGWA, FXB1150_AWA_1150.CI_CNM);
        CEP.TRC(SCCGWA, FXB1150_AWA_1150.CI_ENM);
        CEP.TRC(SCCGWA, FXB1150_AWA_1150.BUY_CCY);
        CEP.TRC(SCCGWA, FXB1150_AWA_1150.BUY_AMT);
        CEP.TRC(SCCGWA, FXB1150_AWA_1150.SELL_CCY);
        CEP.TRC(SCCGWA, FXB1150_AWA_1150.SELL_AMT);
        CEP.TRC(SCCGWA, FXB1150_AWA_1150.TRN_DT);
        CEP.TRC(SCCGWA, FXB1150_AWA_1150.TRN_E_DT);
        CEP.TRC(SCCGWA, FXB1150_AWA_1150.STATUS);
        FXCSDRFX.ID_TYP = FXB1150_AWA_1150.ID_TYP;
        FXCSDRFX.ID_NO = FXB1150_AWA_1150.ID_NO;
        FXCSDRFX.UPD_BR = FXB1150_AWA_1150.ORDER_BR;
        FXCSDRFX.PROD_CD = FXB1150_AWA_1150.PROD_CD;
        FXCSDRFX.REG_CD = FXB1150_AWA_1150.REG_CD;
        FXCSDRFX.CI_NO = FXB1150_AWA_1150.CI_NO;
        FXCSDRFX.TRA_AC = FXB1150_AWA_1150.TRA_AC;
        FXCSDRFX.CI_CNM = FXB1150_AWA_1150.CI_CNM;
        FXCSDRFX.CI_ENM = FXB1150_AWA_1150.CI_ENM;
        FXCSDRFX.BUY_CCY = FXB1150_AWA_1150.BUY_CCY;
        FXCSDRFX.BUY_AMT = FXB1150_AWA_1150.BUY_AMT;
        FXCSDRFX.SELL_CCY = FXB1150_AWA_1150.SELL_CCY;
        FXCSDRFX.SELL_AMT = FXB1150_AWA_1150.SELL_AMT;
        FXCSDRFX.VALUE_DT = FXB1150_AWA_1150.TRN_DT;
        FXCSDRFX.O_END_DT = FXB1150_AWA_1150.TRN_E_DT;
        FXCSDRFX.STATUS = FXB1150_AWA_1150.STATUS;
        S000_CALL_FXZSDRFX();
        CEP.TRC(SCCGWA, FXCSDRFX.UPD_BR);
        CEP.TRC(SCCGWA, FXCSDRFX.BUY_CCY);
        CEP.TRC(SCCGWA, FXCSDRFX.SELL_CCY);
        CEP.TRC(SCCGWA, FXCSDRFX.PROD_CD);
        CEP.TRC(SCCGWA, FXCSDRFX.PROD_NM);
        CEP.TRC(SCCGWA, FXCSDRFX.VALUE_DT);
        CEP.TRC(SCCGWA, FXCSDRFX.O_END_DT);
    }
    public void S000_CALL_FXZSDRFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FX-S-MAIN-DRFX", FXCSDRFX);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
