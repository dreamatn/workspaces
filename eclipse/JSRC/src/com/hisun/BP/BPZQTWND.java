package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZQTWND {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZQTWND";
    String CPN_R_MAINTAIN_TWND = "BP-R-MAINTAIN-TWND";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_K = 0;
    String WS_HOL_DATA = " ";
    BPZQTWND_REDEFINES5 REDEFINES5 = new BPZQTWND_REDEFINES5();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRTWND BPRTWND = new BPRTWND();
    BPCRTWND BPCRTWND = new BPCRTWND();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRCNTY BPRRCNTY = new BPRCNTY();
    SCCGWA SCCGWA;
    BPCQTWND BPCQTWND;
    public void MP(SCCGWA SCCGWA, BPCQTWND BPCQTWND) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCQTWND = BPCQTWND;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZQTWND return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTWND);
        IBS.init(SCCGWA, BPCRTWND);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCQTWND.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_QUERY_HOLI_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCQTWND.INPUT_DAT.EFF_DATE == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_DT_M_INPUT, BPCQTWND.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_HOLI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTWND);
        IBS.init(SCCGWA, BPCRTWND);
        BPRTWND.KEY.CAL_CD = BPCQTWND.INPUT_DAT.CAL_CODE;
        CEP.TRC(SCCGWA, BPCQTWND.INPUT_DAT.CNTY_CODE);
        if (BPCQTWND.INPUT_DAT.CNTY_CODE.trim().length() > 0 
            && BPCQTWND.INPUT_DAT.CAL_CODE.trim().length() == 0) {
            IBS.init(SCCGWA, BPRRCNTY);
            IBS.init(SCCGWA, BPCPRMM);
            BPRRCNTY.KEY.TYP = "CNTY";
            BPRRCNTY.KEY.CD = BPCQTWND.INPUT_DAT.CNTY_CODE;
            BPCPRMM.EFF_DT = BPCQTWND.INPUT_DAT.EFF_DATE;
            BPCPRMM.FUNC = '3';
            S010_CALL_BPZPRMM();
            if (pgmRtn) return;
            BPRTWND.KEY.CAL_CD = BPRRCNTY.DATA_TXT.CALR_CODE;
        }
        BPRTWND.KEY.EFF_DATE = BPCQTWND.INPUT_DAT.EFF_DATE;
        CEP.TRC(SCCGWA, BPCQTWND.INPUT_DAT.CNTY_CODE);
        CEP.TRC(SCCGWA, BPCQTWND.INPUT_DAT.CITY_CODE);
        CEP.TRC(SCCGWA, BPCQTWND.INPUT_DAT.EFF_DATE);
        BPCRTWND.INFO.DATA_LEN = 68;
        BPCRTWND.INFO.POINTER = BPRTWND;
        BPCRTWND.INFO.FUNC = 'I';
        S000_CALL_BPZRTWND();
        if (pgmRtn) return;
        if (BPCRTWND.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_WEEKEND_CD_NOT_FND, BPCQTWND.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_HOL_DATA = BPRTWND.WND_TXT;
        IBS.CPY2CLS(SCCGWA, WS_HOL_DATA, REDEFINES5);
        WS_I = 0;
        for (WS_I = 1; WS_I <= 7; WS_I += 1) {
            BPCQTWND.WND_DATA[WS_I-1].WND_NO = REDEFINES5.WS_HOL_DATA1[WS_I-1].WS_WND_NO;
            BPCQTWND.WND_DATA[WS_I-1].WND_OPT = REDEFINES5.WS_HOL_DATA1[WS_I-1].WS_WND_OPT;
        }
    }
    public void S010_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        BPRRCNTY.DATA_LEN = 78;
        BPCPRMM.DAT_PTR = BPRRCNTY;
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQTWND.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTWND() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINTAIN_TWND, BPCRTWND);
        if (BPCRTWND.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTWND.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQTWND.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCQTWND.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCQTWND = ");
            CEP.TRC(SCCGWA, BPCQTWND);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
