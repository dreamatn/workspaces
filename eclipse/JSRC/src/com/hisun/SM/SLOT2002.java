package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.AI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class SLOT2002 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_ITM_BOOK_FLG = "BK002";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_PROP_TYP = ' ';
    char WS_ITM_FLG = ' ';
    SLCMSG_ERROR_MSG SLCMSG_ERROR_MSG = new SLCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SLCMSAC SLCMSAC = new SLCMSAC();
    AICPQITM AICPQITM = new AICPQITM();
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
        CEP.TRC(SCCGWA, "SLOT2002 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SLB0001_AWA_0001>");
        SLB0001_AWA_0001 = (SLB0001_AWA_0001) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, SLCMSAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (SLB0001_AWA_0001.BR == 0) {
            WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_BR_MUST_INPUT;
            WS_FLD_NO = SLB0001_AWA_0001.BR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SLB0001_AWA_0001.CCY.trim().length() == 0) {
            WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_CCY_MUST_INPUT;
            WS_FLD_NO = SLB0001_AWA_0001.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SLB0001_AWA_0001.ITM.trim().length() == 0) {
            WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_ITM_MUST_INPUT;
            WS_FLD_NO = SLB0001_AWA_0001.ITM_NO;
        } else {
            B011_CHK_ITM_INVLID();
            if (pgmRtn) return;
        }
        if (WS_PROP_TYP == '2') {
            if (SLB0001_AWA_0001.CI_NO.trim().length() == 0) {
                WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_CINO_MUST_INPUT;
                WS_FLD_NO = SLB0001_AWA_0001.CI_NO_NO;
            }
        } else {
            if (SLB0001_AWA_0001.PROP_CD == 0) {
                WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_PCD_MUST_INPUT;
                WS_FLD_NO = SLB0001_AWA_0001.PROP_CD_NO;
            }
        }
        WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        SLCMSAC.DATA.BR = SLB0001_AWA_0001.BR;
        SLCMSAC.DATA.CCY = SLB0001_AWA_0001.CCY;
        SLCMSAC.DATA.ITM = SLB0001_AWA_0001.ITM;
        SLCMSAC.DATA.PROP_CD = SLB0001_AWA_0001.PROP_CD;
        SLCMSAC.DATA.CI_NO = SLB0001_AWA_0001.CI_NO;
        SLCMSAC.DATA.AC_NAME = SLB0001_AWA_0001.AC_NAME;
        SLCMSAC.DATA.BAL = SLB0001_AWA_0001.AC_BAL;
        SLCMSAC.FUNC = 'M';
        S000_CALL_SLZSAC();
        if (pgmRtn) return;
    }
    public void B011_CHK_ITM_INVLID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQITM);
        AICPQITM.INPUT_DATA.BOOK_FLG = K_ITM_BOOK_FLG;
        AICPQITM.INPUT_DATA.NO = SLB0001_AWA_0001.ITM;
        S000_CALL_AIZPQITM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.STS);
        CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.ITM_LVL);
        CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.SL_FLG);
        if (AICPQITM.OUTPUT_DATA.STS != 'A') {
            CEP.ERR(SCCGWA, SLCMSG_ERROR_MSG.SL_ITM_NOT_ACT);
        }
        if (AICPQITM.OUTPUT_DATA.ITM_LVL != '3') {
            CEP.ERR(SCCGWA, SLCMSG_ERROR_MSG.SL_ITM_NOT_LVL3);
        }
        WS_PROP_TYP = AICPQITM.OUTPUT_DATA.SL_FLG;
        if ((WS_PROP_TYP != '1' 
            && WS_PROP_TYP != 'THRU' 
            && WS_PROP_TYP != '4')) {
            CEP.ERR(SCCGWA, SLCMSG_ERROR_MSG.SL_INVALID_PTY);
        }
    }
    public void S000_CALL_SLZSAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SL-MAIN-AC", SLCMSAC);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        CEP.TRC(SCCGWA, AICPQITM.RC);
        if (AICPQITM.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
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
