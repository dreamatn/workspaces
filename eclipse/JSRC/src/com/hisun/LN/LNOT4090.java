package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT4090 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_TOT_WAV = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCSILW LNCSILW = new LNCSILW();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNCRCMMT LNCRCMMT = new LNCRCMMT();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    SCCGWA SCCGWA;
    LNB4090_AWA_4090 LNB4090_AWA_4090;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT4090 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB4090_AWA_4090>");
        LNB4090_AWA_4090 = (LNB4090_AWA_4090) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB4090_AWA_4090.CTA_NO);
        CEP.TRC(SCCGWA, LNB4090_AWA_4090.BR_NO);
        CEP.TRC(SCCGWA, LNB4090_AWA_4090.CI_NO);
        CEP.TRC(SCCGWA, LNB4090_AWA_4090.CI_CNM);
        CEP.TRC(SCCGWA, LNB4090_AWA_4090.CCY);
        CEP.TRC(SCCGWA, LNB4090_AWA_4090.TOT_PRIN);
        CEP.TRC(SCCGWA, LNB4090_AWA_4090.TOT_BAL);
        CEP.TRC(SCCGWA, LNB4090_AWA_4090.TOT_INT);
        CEP.TRC(SCCGWA, LNB4090_AWA_4090.TOT_LC);
        CEP.TRC(SCCGWA, LNB4090_AWA_4090.TOT_RINT);
        CEP.TRC(SCCGWA, LNB4090_AWA_4090.TOT_WAV);
        CEP.TRC(SCCGWA, LNB4090_AWA_4090.WAV_INT);
        CEP.TRC(SCCGWA, LNB4090_AWA_4090.WAV_LC);
        CEP.TRC(SCCGWA, LNB4090_AWA_4090.WAV_RINT);
        if (LNB4090_AWA_4090.TOT_WAV != 0 
            && (LNB4090_AWA_4090.WAV_INT != 0 
            || LNB4090_AWA_4090.WAV_LC != 0 
            || LNB4090_AWA_4090.WAV_RINT != 0)) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_INPUT_WAV;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB4090_AWA_4090.TOT_WAV != 0 
            && LNB4090_AWA_4090.TOT_BAL != 0 
            && LNB4090_AWA_4090.TOT_WAV > LNB4090_AWA_4090.TOT_BAL) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_TOT_WAV;
            WS_FLD_NO = (short) LNB4090_AWA_4090.TOT_WAV;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_TOT_WAV = LNB4090_AWA_4090.TOT_INT + LNB4090_AWA_4090.TOT_LC + LNB4090_AWA_4090.TOT_RINT;
        if (LNB4090_AWA_4090.TOT_WAV != 0 
            && WS_TOT_WAV != 0 
            && LNB4090_AWA_4090.TOT_WAV > WS_TOT_WAV) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_WAV_AMT;
            WS_FLD_NO = (short) LNB4090_AWA_4090.TOT_WAV;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB4090_AWA_4090.CTA_NO.trim().length() > 0) {
            IBS.init(SCCGWA, LNRCONT);
            IBS.init(SCCGWA, LNCRCONT);
            LNRCONT.KEY.CONTRACT_NO = LNB4090_AWA_4090.CTA_NO;
            LNCRCONT.FUNC = 'I';
            S000_CALL_LNZRCONT();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B011_CHECK_ICTL_STS();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSILW);
        LNCSILW.CTA_NO = LNB4090_AWA_4090.CTA_NO;
        LNCSILW.BR_NO = LNB4090_AWA_4090.BR_NO;
        LNCSILW.CI_NO = LNB4090_AWA_4090.CI_NO;
        LNCSILW.CI_CNM = LNB4090_AWA_4090.CI_CNM;
        LNCSILW.CCY = LNB4090_AWA_4090.CCY;
        LNCSILW.TOT_PRIN = LNB4090_AWA_4090.TOT_PRIN;
        LNCSILW.TOT_BAL = LNB4090_AWA_4090.TOT_BAL;
        LNCSILW.TOT_INT = LNB4090_AWA_4090.TOT_INT;
        LNCSILW.TOT_LC = LNB4090_AWA_4090.TOT_LC;
        LNCSILW.TOT_RINT = LNB4090_AWA_4090.TOT_RINT;
        LNCSILW.TOT_WAV = LNB4090_AWA_4090.TOT_WAV;
        LNCSILW.WAV_INT = LNB4090_AWA_4090.WAV_INT;
        LNCSILW.WAV_LC = LNB4090_AWA_4090.WAV_LC;
        LNCSILW.WAV_RINT = LNB4090_AWA_4090.WAV_RINT;
        S000_CALL_LNZSILW();
        if (pgmRtn) return;
    }
    public void B011_CHECK_ICTL_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSSTBL);
        LNCSSTBL.CON_NO = LNB4090_AWA_4090.CTA_NO;
        LNCSSTBL.TR_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = LNCSSTBL.TR_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCSSTBL.TR_CODE = "0" + LNCSSTBL.TR_CODE;
        CEP.TRC(SCCGWA, LNCSSTBL.TR_CODE);
        S000_CALL_LNZSSTBL();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZSSTBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-CHECK-CI-STS", LNCSSTBL);
        if (LNCSSTBL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSSTBL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSILW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-INLC-WAV", LNCSILW);
        if (LNCSILW.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSILW.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RETURN_INFO != 'F') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
