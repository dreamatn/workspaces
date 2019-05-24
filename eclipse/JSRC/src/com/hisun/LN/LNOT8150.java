package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;
import com.hisun.DB.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8150 {
    BigDecimal bigD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_BANK_99 = 999999;
    LNOT8150_WS_MSGID WS_MSGID = new LNOT8150_WS_MSGID();
    String WS_ERR_INFO = " ";
    double WS_N_RATE = 0;
    LNOT8150_WS_OUT_DATA WS_OUT_DATA = new LNOT8150_WS_OUT_DATA();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCFMT SCCFMT = new SCCFMT();
    BPRPARP BPRPARP = new BPRPARP();
    BPRPARM BPRPARM = new BPRPARM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCRINTM BPCRINTM = new BPCRINTM();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    LNB8150_AWA_8150 LNB8150_AWA_8150;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT8150 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB8150_AWA_8150>");
        LNB8150_AWA_8150 = (LNB8150_AWA_8150) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
        B030_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB8150_AWA_8150.FLT_MTH == '1' 
            && LNB8150_AWA_8150.RATE_VAR == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_NVAR_MUST_INPUT, WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB8150_AWA_8150.FLT_MTH == '2' 
            && LNB8150_AWA_8150.RATE_PCT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_NPCT_MUST_INPUT, WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRINTM);
        BPCRINTM.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCRINTM.BASE_TYP = LNB8150_AWA_8150.RATE_TYP;
        BPCRINTM.TENOR = LNB8150_AWA_8150.RATE_PER;
        BPCRINTM.CCY = LNB8150_AWA_8150.CCY;
        BPCRINTM.DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCRINTM.FUNC = 'I';
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, LNB8150_AWA_8150.RATE_TYP);
        CEP.TRC(SCCGWA, LNB8150_AWA_8150.RATE_PER);
        CEP.TRC(SCCGWA, LNB8150_AWA_8150.CCY);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPCRINTM.BASE_TYP);
        CEP.TRC(SCCGWA, BPCRINTM.TENOR);
        CEP.TRC(SCCGWA, BPCRINTM.CCY);
        CEP.TRC(SCCGWA, BPCRINTM.DT);
        S000_CALL_BPZRINTM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LLLLLL");
        if (BPCRINTM.RETURN_INFO == 'F') {
            WS_OUT_DATA.WS_RATE_INT = BPCRINTM.RATE;
        } else {
            IBS.init(SCCGWA, BPCRINTM);
            BPCRINTM.BR = K_BANK_99;
            BPCRINTM.BASE_TYP = LNB8150_AWA_8150.RATE_TYP;
            BPCRINTM.TENOR = LNB8150_AWA_8150.RATE_PER;
            BPCRINTM.CCY = LNB8150_AWA_8150.CCY;
            BPCRINTM.DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCRINTM.FUNC = 'I';
            CEP.TRC(SCCGWA, BPCRINTM.BR);
            CEP.TRC(SCCGWA, BPCRINTM.BASE_TYP);
            CEP.TRC(SCCGWA, BPCRINTM.TENOR);
            CEP.TRC(SCCGWA, BPCRINTM.CCY);
            CEP.TRC(SCCGWA, BPCRINTM.DT);
            S000_CALL_BPZRINTM();
            if (pgmRtn) return;
            if (BPCRINTM.RETURN_INFO == 'F') {
                WS_OUT_DATA.WS_RATE_INT = BPCRINTM.RATE;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT_RAT, WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNB8150_AWA_8150.FLT_MTH == '0') {
            WS_N_RATE = WS_OUT_DATA.WS_RATE_INT;
        } else if (LNB8150_AWA_8150.FLT_MTH == '1') {
            WS_N_RATE = WS_OUT_DATA.WS_RATE_INT + LNB8150_AWA_8150.RATE_VAR;
            bigD = new BigDecimal(WS_N_RATE);
            WS_N_RATE = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
        } else if (LNB8150_AWA_8150.FLT_MTH == '2') {
            WS_N_RATE = WS_OUT_DATA.WS_RATE_INT * ( 1 + LNB8150_AWA_8150.RATE_PCT / 100 );
            bigD = new BigDecimal(WS_N_RATE);
            WS_N_RATE = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
        } else if (LNB8150_AWA_8150.FLT_MTH == '3') {
            WS_N_RATE = WS_OUT_DATA.WS_RATE_INT * ( 1 + LNB8150_AWA_8150.RATE_PCT / 100 ) + LNB8150_AWA_8150.RATE_VAR;
            bigD = new BigDecimal(WS_N_RATE);
            WS_N_RATE = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
        } else if (LNB8150_AWA_8150.FLT_MTH == '4') {
            WS_N_RATE = ( WS_OUT_DATA.WS_RATE_INT + LNB8150_AWA_8150.RATE_VAR ) * ( 1 + LNB8150_AWA_8150.RATE_PCT / 100 );
            bigD = new BigDecimal(WS_N_RATE);
            WS_N_RATE = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FLT_MTH_VALID, WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_OUT_DATA.WS_IN_RATE = WS_N_RATE;
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        SCCFMT.FMTID = "LN815";
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 24;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZRINTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-INTR-MAINT", BPCRINTM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
