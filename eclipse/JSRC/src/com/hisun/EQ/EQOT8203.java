package com.hisun.EQ;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class EQOT8203 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_BSZ_BANKID = "01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_DICP_FLG = ' ';
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
        CEP.TRC(SCCGWA, "EQOT8203 return!");
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
        CEP.TRC(SCCGWA, EQB0002_AWA_0002.DT_OLD);
        CEP.TRC(SCCGWA, EQB0002_AWA_0002.PROC_DT);
        CEP.TRC(SCCGWA, EQB0002_AWA_0002.CPN_TYP);
        CEP.TRC(SCCGWA, EQB0002_AWA_0002.EX_CL_PT);
        CEP.TRC(SCCGWA, EQB0002_AWA_0002.CO_CL_PT);
        CEP.TRC(SCCGWA, EQB0002_AWA_0002.IN_CL_PT);
        CEP.TRC(SCCGWA, EQB0002_AWA_0002.O_CL_PT);
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
        } else {
            WS_DICP_FLG = EQB0002_AWA_0002.DICP_FLG;
            if ((WS_DICP_FLG != '1' 
                && WS_DICP_FLG != '2')) {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INVALID_DICFLG;
                WS_FLD_NO = EQB0002_AWA_0002.DICP_FLG_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (EQB0002_AWA_0002.DICP_DT == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_DICDT_MUST_INPUT;
            WS_FLD_NO = EQB0002_AWA_0002.DICP_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (EQB0002_AWA_0002.DT_OLD == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_PROCDT_MUST_INPUT;
            WS_FLD_NO = EQB0002_AWA_0002.DT_OLD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (EQB0002_AWA_0002.PROC_DT == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_PROCDT_MUST_INPUT;
            WS_FLD_NO = EQB0002_AWA_0002.PROC_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (EQB0002_AWA_0002.DICP_FLG == '2' 
            && EQB0002_AWA_0002.CPN_TYP == ' ') {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_CPNTYP_MUST_INPUT;
            WS_FLD_NO = EQB0002_AWA_0002.CPN_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
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
        EQCSDVC.DATA.DT_OLD = EQB0002_AWA_0002.DT_OLD;
        EQCSDVC.DATA.PROC_DT = EQB0002_AWA_0002.PROC_DT;
        EQCSDVC.DATA.CPN_TYP = EQB0002_AWA_0002.CPN_TYP;
        EQCSDVC.DATA.EX_CL_PT = EQB0002_AWA_0002.EX_CL_PT;
        EQCSDVC.DATA.CO_CL_PT = EQB0002_AWA_0002.CO_CL_PT;
        EQCSDVC.DATA.IN_CL_PT = EQB0002_AWA_0002.IN_CL_PT;
        EQCSDVC.DATA.O_CL_PT = EQB0002_AWA_0002.O_CL_PT;
        EQCSDVC.FUNC = 'U';
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
