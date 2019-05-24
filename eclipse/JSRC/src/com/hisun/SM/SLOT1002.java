package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class SLOT1002 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    SLCMSG_ERROR_MSG SLCMSG_ERROR_MSG = new SLCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SLCMPRP SLCMPRP = new SLCMPRP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SLB0001_AWA_0001 SLB0001_AWA_0001;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SLOT1002 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SLB0001_AWA_0001>");
        SLB0001_AWA_0001 = (SLB0001_AWA_0001) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, SLCMPRP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (SLB0001_AWA_0001.PROP_TYP == ' ') {
            WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_PTY_MUST_INPUT;
            WS_FLD_NO = SLB0001_AWA_0001.PROP_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        } else {
            if (SLB0001_AWA_0001.PROP_TYP == '1') {
                if (SLB0001_AWA_0001.SWIFT_CO.trim().length() == 0 
                    && SLB0001_AWA_0001.IB_NAME.trim().length() == 0) {
                    WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_SWC_MUST_INPUT;
                    WS_FLD_NO = SLB0001_AWA_0001.SWIFT_CO_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (SLB0001_AWA_0001.PROP_TYP == '2') {
                WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_AVOID_PTY2;
                WS_FLD_NO = SLB0001_AWA_0001.PROP_TYP_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            } else if (SLB0001_AWA_0001.PROP_TYP == '3') {
                if (SLB0001_AWA_0001.BND_CD.trim().length() == 0) {
                    WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_BND_MUST_INPUT;
                    WS_FLD_NO = SLB0001_AWA_0001.BND_CD_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (SLB0001_AWA_0001.PROP_TYP == '4') {
                if (SLB0001_AWA_0001.MET_FINE.trim().length() == 0 
                    && SLB0001_AWA_0001.PROP_CD == 0) {
                    WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_MEF_MUST_INPUT;
                    WS_FLD_NO = SLB0001_AWA_0001.MET_FINE_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else {
                WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_INVALID_PTY;
                WS_FLD_NO = SLB0001_AWA_0001.PROP_TYP_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        SLCMPRP.DATA.PROP_TYP = SLB0001_AWA_0001.PROP_TYP;
        SLCMPRP.DATA.PROP_CD = SLB0001_AWA_0001.PROP_CD;
        SLCMPRP.DATA.SWIFT_CO = SLB0001_AWA_0001.SWIFT_CO;
        SLCMPRP.DATA.IB_NAME = SLB0001_AWA_0001.IB_NAME;
        SLCMPRP.DATA.BND_CD = SLB0001_AWA_0001.BND_CD;
        SLCMPRP.DATA.BND_TYPE = SLB0001_AWA_0001.BND_TYPE;
        SLCMPRP.DATA.MET_FINE = SLB0001_AWA_0001.MET_FINE;
        SLCMPRP.DATA.PROP_NAME = SLB0001_AWA_0001.PROP_NAM;
        SLCMPRP.DATA.CNTY_CD = SLB0001_AWA_0001.CNTY_CD;
        SLCMPRP.DATA.RSK_VAL = SLB0001_AWA_0001.RSK_VAL;
        SLCMPRP.DATA.IB_TYP = SLB0001_AWA_0001.IB_TYP;
        SLCMPRP.DATA.REMARK1 = SLB0001_AWA_0001.REMARK1;
        SLCMPRP.FUNC = 'M';
        S000_CALL_SLZSPROP();
        if (pgmRtn) return;
    }
    public void S000_CALL_SLZSPROP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SL-MAIN-PRP", SLCMPRP);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
