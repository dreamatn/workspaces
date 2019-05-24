package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSQTYP {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SCCCKDT SCCCKDT = new SCCCKDT();
    String PGM_SCSSCKDT = "SCSSCKDT";
    int K_MAX_CNT = 500;
    int K_MAX_ROW = 50;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSQTYP_WS_OUT_DATA WS_OUT_DATA = new BPZSQTYP_WS_OUT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTDAYE BPCTDAYE = new BPCTDAYE();
    BPRDAYE BPRDAYE = new BPRDAYE();
    BPCPQBCH BPCPQBCH = new BPCPQBCH();
    SCCGWA SCCGWA;
    BPCSQTYP BPCSQTYP;
    public void MP(SCCGWA SCCGWA, BPCSQTYP BPCSQTYP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSQTYP = BPCSQTYP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSQTYP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSQTYP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_BROWSE_RECORDS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, BPCSQTYP.DATE1);
        SCCCKDT.DATE = BPCSQTYP.DATE1;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, BPCSQTYP.DATE2);
        SCCCKDT.DATE = BPCSQTYP.DATE2;
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSQTYP.DATE1 > BPCSQTYP.DATE2) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_S_DATE_GT_E_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_RECORDS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTDAYE);
        IBS.init(SCCGWA, BPRDAYE);
        BPRDAYE.KEY.TYPE = 'C';
        IBS.init(SCCGWA, BPCPQBCH);
        BPCPQBCH.ACCT = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQBCH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQBCH.BCH);
        CEP.TRC(SCCGWA, BPCSQTYP.CITY_CD);
        if (BPCSQTYP.CITY_CD.trim().length() == 0) {
            BPRDAYE.KEY.RGN = "%%%%%%";
        } else {
            BPRDAYE.KEY.RGN = BPCSQTYP.CITY_CD;
        }
        CEP.TRC(SCCGWA, BPRDAYE.KEY.RGN);
        BPRDAYE.KEY.DATE = BPCSQTYP.DATE1;
        BPCTDAYE.INFO.FUNC = 'B';
        BPCTDAYE.INFO.OPT = 'S';
        BPCTDAYE.INFO.POINTER = BPRDAYE;
        S000_CALL_BPZTDAYE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "KIA START BROWSING");
        CEP.TRC(SCCGWA, BPRDAYE);
        B011_OUTPUT_TITLE();
        if (pgmRtn) return;
        BPCTDAYE.RETURN_INFO = ' ';
        IBS.init(SCCGWA, BPCTDAYE.RC);
        BPCTDAYE.INFO.FUNC = 'B';
        BPCTDAYE.INFO.OPT = 'N';
        BPCTDAYE.INFO.POINTER = BPRDAYE;
        S000_CALL_BPZTDAYE();
        if (pgmRtn) return;
        WS_OUT_DATA.WS_DATE = BPRDAYE.KEY.DATE;
        CEP.TRC(SCCGWA, BPRDAYE.CHARACTER);
        CEP.TRC(SCCGWA, BPRDAYE.DATE_TYPE);
        for (WS_CNT = 1; WS_CNT < K_MAX_CNT 
            && BPCTDAYE.RETURN_INFO != 'N' 
            && BPRDAYE.KEY.DATE <= BPCSQTYP.DATE2 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            B012_OUTPUT_DETAIL();
            if (pgmRtn) return;
            BPCTDAYE.RETURN_INFO = ' ';
            IBS.init(SCCGWA, BPCTDAYE.RC);
            BPCTDAYE.INFO.FUNC = 'B';
            BPCTDAYE.INFO.OPT = 'N';
            S000_CALL_BPZTDAYE();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCTDAYE.RC);
        BPCTDAYE.RETURN_INFO = ' ';
        BPCTDAYE.INFO.FUNC = 'B';
        BPCTDAYE.INFO.OPT = 'E';
        S000_CALL_BPZTDAYE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "KIA START BROWSING4");
        CEP.TRC(SCCGWA, BPRDAYE);
    }
    public void B011_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 44;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B012_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_DATA);
        WS_OUT_DATA.WS_DATE = BPRDAYE.KEY.DATE;
        WS_OUT_DATA.WS_DATE_TYPE = BPRDAYE.DATE_TYPE;
        WS_OUT_DATA.WS_CHARACTER = BPRDAYE.CHARACTER;
        WS_OUT_DATA.WS_EXCH_FLG = BPRDAYE.EXCH_FLG;
        WS_OUT_DATA.WS_CITY_CD = BPRDAYE.KEY.RGN;
        SCCMPAG.FUNC = 'D';
        CEP.TRC(SCCGWA, "KIAKIA");
        CEP.TRC(SCCGWA, WS_OUT_DATA);
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_DATA);
        SCCMPAG.DATA_LEN = 44;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQBCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-BCH", BPCPQBCH);
    }
    public void S000_CALL_BPZTDAYE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-DAYE", BPCTDAYE);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
