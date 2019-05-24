package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZQCCY {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_MAINTAIN_CCY = "BP-RESOURCE-CCY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCRCCY BPCRCCY = new BPCRCCY();
    SCCGWA SCCGWA;
    BPCQCCY BPCQCCY;
    public void MP(SCCGWA SCCGWA, BPCQCCY BPCQCCY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCQCCY = BPCQCCY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_QUERY_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZQCCY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCCY);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCQCCY.RC);
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CCY);
        CEP.TRC(SCCGWA, "EEEEEEE");
    }
    public void B000_QUERY_PROCESS() throws IOException,SQLException,Exception {
        B010_QUERY_CHECK_INPUT();
        if (pgmRtn) return;
        BPCRCCY.OP_FUNC = 'I';
        CEP.TRC(SCCGWA, BPCRCCY.DATA);
        S000_TRANS_BPCRCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRCCY.DATA);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CCY);
        S000_CALL_BPZRCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRCCY.DATA);
        S010_TRANS_QCCY_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRCCY.DATA);
    }
    public void B010_QUERY_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CCY);
        CEP.TRC(SCCGWA, "FFFFFFFF");
        if ((BPCQCCY.DATA.CCY.trim().length() == 0) 
            && (BPCQCCY.DATA.CCY_CD == 0)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NEED_CCY_OR_CCYCD, BPCQCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_TRANS_BPCRCCY() throws IOException,SQLException,Exception {
        BPCRCCY.DATA.CCY = BPCQCCY.DATA.CCY;
    }
    public void S010_TRANS_QCCY_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCQCCY.RC);
        BPCQCCY.DATA.CCY = BPCRCCY.DATA.CCY;
        BPCQCCY.DATA.CCY_CD = BPCRCCY.DATA.CCY_CD;
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CCY_CD);
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CCY_CD);
        BPCQCCY.DATA.CUR_NM = BPCRCCY.DATA.CUR_NM;
        BPCQCCY.DATA.EFF_DT = BPCRCCY.DATA.EFF_DT;
        BPCQCCY.DATA.EXP_DT = BPCRCCY.DATA.EXP_DT;
        BPCQCCY.DATA.CNTY_CD = BPCRCCY.DATA.CNTY_CD;
        BPCQCCY.DATA.CITY_CD = BPCRCCY.DATA.CITY_CD;
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CITY_CD);
        BPCQCCY.DATA.UNIT_CURU_NAME = BPCRCCY.DATA.UNIT_CURU_NAME;
        BPCQCCY.DATA.CENT_CURU_NAME = BPCRCCY.DATA.CENT_CURU_NAME;
        BPCQCCY.DATA.RGN_CCY = BPCRCCY.DATA.RGN_CCY;
        if (BPCRCCY.DATA.DEC_MTH == ' ') BPCQCCY.DATA.DEC_MTH = 0;
        else BPCQCCY.DATA.DEC_MTH = Short.parseShort(""+BPCRCCY.DATA.DEC_MTH);
        BPCQCCY.DATA.CASH_MTH = BPCRCCY.DATA.CASH_MTH;
        BPCQCCY.DATA.RND_MTH = BPCRCCY.DATA.RND_MTH;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.RND_MTH);
        BPCQCCY.DATA.TR_FLG = BPCRCCY.DATA.TR_FLG;
        BPCQCCY.DATA.CASH_FLG = BPCRCCY.DATA.CASH_FLG;
        BPCQCCY.DATA.CHGU_MTH = BPCRCCY.DATA.CHGU_MTH;
        BPCQCCY.DATA.EXH_FLG = BPCRCCY.DATA.EXH_FLG;
        BPCQCCY.DATA.CALR_STD = BPCRCCY.DATA.CALR_STD;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CALR_STD);
        BPCQCCY.DATA.CAL_CD = BPCRCCY.DATA.CAL_CD;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CAL_CD);
        BPCQCCY.DATA.UPT_DT = BPCRCCY.DATA.UPT_DT;
        BPCQCCY.DATA.UPT_TLR = BPCRCCY.DATA.UPT_TLR;
        BPCQCCY.DATA.ISR_DAYS = BPCRCCY.DATA.ISR_DAYS;
        BPCQCCY.DATA.BAL_DAYS = BPCRCCY.DATA.BAL_DAYS;
        BPCQCCY.DATA.CHG_CCY = BPCRCCY.DATA.CHG_CCY;
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CALR_STD);
        if (BPCRCCY.DATA.CALR_STD.equalsIgnoreCase("01")) {
            BPCQCCY.DATA.STD_DAYS = 360;
        } else if (BPCRCCY.DATA.CALR_STD.equalsIgnoreCase("02")) {
            BPCQCCY.DATA.STD_DAYS = 365;
        } else if (BPCRCCY.DATA.CALR_STD.equalsIgnoreCase("03")) {
            BPCQCCY.DATA.STD_DAYS = 366;
        } else if (BPCRCCY.DATA.CALR_STD.equalsIgnoreCase("04")) {
            BPCQCCY.DATA.STD_DAYS = 30;
        } else if (BPCRCCY.DATA.CALR_STD.equalsIgnoreCase("05")) {
            BPCQCCY.DATA.STD_DAYS = 31;
        } else {
            BPCQCCY.DATA.STD_DAYS = 0;
        }
    }
    public void S000_CALL_BPZRCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-RESOURCE-CCY", BPCRCCY);
        CEP.TRC(SCCGWA, BPCRCCY.RC);
        if (BPCRCCY.RC.RTNCODE == 0) {
            BPCQCCY.RC.RTNCODE = 0;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "DEVSOS-BPZQCCY");
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CCY);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CCY_CD);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.TR_FLG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCQCCY.RC.RTNCODE != 0) {
            CEP.TRC(SCCGWA, " BPCQCCY = ");
            CEP.TRC(SCCGWA, BPCQCCY);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
