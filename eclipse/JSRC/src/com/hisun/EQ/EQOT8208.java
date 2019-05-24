package com.hisun.EQ;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class EQOT8208 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char K_STS_DFH = 'C';
    char K_STS_DSC = 'D';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_REC_STS = ' ';
    char WS_FH_FLG = ' ';
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    EQCSDVC EQCSDVC = new EQCSDVC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    EQB0002_AWA_0002 EQB0002_AWA_0002;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "EQOT8208 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "EQB0002_AWA_0002>");
        EQB0002_AWA_0002 = (EQB0002_AWA_0002) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EQB0002_AWA_0002.EQ_BKID);
        CEP.TRC(SCCGWA, EQB0002_AWA_0002.DICP_FLG);
        CEP.TRC(SCCGWA, EQB0002_AWA_0002.DICP_DT);
        CEP.TRC(SCCGWA, EQB0002_AWA_0002.PROC_DT);
        CEP.TRC(SCCGWA, EQB0002_AWA_0002.REQ_DT);
        CEP.TRC(SCCGWA, EQB0002_AWA_0002.EX_CL_PT);
        CEP.TRC(SCCGWA, EQB0002_AWA_0002.CO_CL_PT);
        CEP.TRC(SCCGWA, EQB0002_AWA_0002.IN_CL_PT);
        CEP.TRC(SCCGWA, EQB0002_AWA_0002.O_CL_PT);
        CEP.TRC(SCCGWA, EQB0002_AWA_0002.REC_STS);
        CEP.TRC(SCCGWA, EQB0002_AWA_0002.FH_FLG);
        if (EQB0002_AWA_0002.EQ_BKID.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_BANKID_MUST_INPUT;
            WS_FLD_NO = EQB0002_AWA_0002.EQ_BKID_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (EQB0002_AWA_0002.DICP_FLG == ' ') {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_DICFLG_MUST_INPUT;
            WS_FLD_NO = EQB0002_AWA_0002.DICP_FLG_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (EQB0002_AWA_0002.DICP_DT == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_DICDT_MUST_INPUT;
            WS_FLD_NO = EQB0002_AWA_0002.DICP_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (EQB0002_AWA_0002.PROC_DT == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_PROCDT_MUST_INPUT;
            WS_FLD_NO = EQB0002_AWA_0002.PROC_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (EQB0002_AWA_0002.REC_STS == ' ') {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_RECSTS_MUST_INPUT;
            WS_FLD_NO = EQB0002_AWA_0002.REC_STS_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        } else {
            WS_REC_STS = EQB0002_AWA_0002.REC_STS;
            if ((WS_REC_STS != 'C' 
                && WS_REC_STS != 'D')) {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_RECSTS_NOTCD;
                WS_FLD_NO = EQB0002_AWA_0002.REC_STS_NO;
            }
        }
        if (EQB0002_AWA_0002.FH_FLG == ' ') {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_FHFLG_MUST_INPUT;
            WS_FLD_NO = EQB0002_AWA_0002.FH_FLG_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        } else {
            WS_FH_FLG = EQB0002_AWA_0002.FH_FLG;
            if ((WS_FH_FLG != 'Y' 
                && WS_FH_FLG != 'N')) {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INVALID_FH_FLG;
                WS_FLD_NO = EQB0002_AWA_0002.FH_FLG_NO;
            }
        }
        WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCSDVC);
        EQCSDVC.DATA.EQ_BKID = EQB0002_AWA_0002.EQ_BKID;
        EQCSDVC.DATA.DICP_FLG = EQB0002_AWA_0002.DICP_FLG;
        EQCSDVC.DATA.DICP_DT = EQB0002_AWA_0002.DICP_DT;
        EQCSDVC.DATA.PROC_DT = EQB0002_AWA_0002.PROC_DT;
        EQCSDVC.DATA.REC_STS = EQB0002_AWA_0002.REC_STS;
        EQCSDVC.DATA.FH_FLG = EQB0002_AWA_0002.FH_FLG;
        EQCSDVC.FUNC = 'F';
        S000_CALL_EQZSDVC();
        if (pgmRtn) return;
    }
    public void S000_CALL_EQZSDVC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "EQ-S-MAIN-DICP", EQCSDVC);
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
