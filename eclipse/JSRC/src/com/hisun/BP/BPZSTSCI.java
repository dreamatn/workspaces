package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTSCI {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "SCZ01";
    String CPN_R_ADW_TLSB = "BP-R-ADW-TLSB       ";
    String CPN_R_ADW_SCHS = "BP-R-ADW-SCHS       ";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    String WS_CODE_NO = " ";
    int WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOTLSC BPCOTLSC = new BPCOTLSC();
    BPCOTLSC BPCOTSCI = new BPCOTLSC();
    BPRTLSC BPRTLSC = new BPRTLSC();
    BPRSCHS BPRSCHS = new BPRSCHS();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCRTLSB BPCRTLSB = new BPCRTLSB();
    BPCRSCHS BPCRSCHS = new BPCRSCHS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRTRT BPRTRTT;
    SCCAWAC SCCAWAC;
    BPCSTSCI BPCSTSCI;
    public void MP(SCCGWA SCCGWA, BPCSTSCI BPCSTSCI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTSCI = BPCSTSCI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSTSCI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        IBS.init(SCCGWA, BPCRTLSB);
        IBS.init(SCCGWA, BPRTLSC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_MAIN_TSCI_BROWSE();
        if (pgmRtn) return;
    }
    public void B010_MAIN_TSCI_BROWSE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSTSCI.SC_TYPE);
        CEP.TRC(SCCGWA, BPCSTSCI.SC_STS);
        CEP.TRC(SCCGWA, BPCSTSCI.CODE_NO);
        CEP.TRC(SCCGWA, BPCSTSCI.POOL_BOX_NO);
        CEP.TRC(SCCGWA, BPRTLSC.KEY.BR);
        BPCRTLSB.FUNC = 'B';
        BPRTLSC.KEY.BR = BPCSTSCI.BR;
        BPRTLSC.KEY.PL_BOX_NO = BPCSTSCI.POOL_BOX_NO;
        BPRTLSC.KEY.CODE_NO = BPCSTSCI.CODE_NO;
        BPRTLSC.SC_TYPE = BPCSTSCI.SC_TYPE;
        BPRTLSC.SC_STS = BPCSTSCI.SC_STS;
        S000_CALL_BPZRTLSB();
        if (pgmRtn) return;
        BPCRTLSB.FUNC = 'R';
        S000_CALL_BPZRTLSB();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCRTLSB.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            BPCRTLSB.FUNC = 'R';
            S000_CALL_BPZRTLSB();
            if (pgmRtn) return;
        }
        BPCRTLSB.FUNC = 'E';
        S000_CALL_BPZRTLSB();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTSCI);
        CEP.TRC(SCCGWA, BPRTLSC.KEY.BR);
        CEP.TRC(SCCGWA, BPRTLSC.KEY.PL_BOX_NO);
        CEP.TRC(SCCGWA, BPRTLSC.SC_DATE);
        CEP.TRC(SCCGWA, BPRTLSC.SC_TYPE);
        CEP.TRC(SCCGWA, BPRTLSC.SC_STS);
        CEP.TRC(SCCGWA, BPRTLSC.KEY.CODE_NO);
        CEP.TRC(SCCGWA, BPRTLSC.MC_NO);
        CEP.TRC(SCCGWA, BPRTLSC.UPD_TLR);
        BPCOTSCI.BR = BPRTLSC.KEY.BR;
        BPCOTSCI.POOL_BOX_NO = BPRTLSC.KEY.PL_BOX_NO;
        BPCOTSCI.SC_DATE = BPRTLSC.SC_DATE;
        BPCOTSCI.SC_TYPE = BPRTLSC.SC_TYPE;
        BPCOTSCI.SC_STS = BPRTLSC.SC_STS;
        BPCOTSCI.CODE_NO = BPRTLSC.KEY.CODE_NO;
        CEP.TRC(SCCGWA, BPCOTSCI.CODE_NO);
        BPCOTSCI.MC_NO = BPRTLSC.MC_NO;
        BPCOTSCI.CRT_DATE = BPRTLSC.UPD_DT;
        BPCOTSCI.CRT_TLR = BPRTLSC.UPD_TLR;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOTSCI);
        SCCMPAG.DATA_LEN = 318;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B300_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 318;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 6;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void S000_CALL_BPZRTLSB() throws IOException,SQLException,Exception {
        BPCRTLSB.POINTER = BPRTLSC;
        BPCRTLSB.LEN = 736;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLSB, BPCRTLSB);
        if (BPCRTLSB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTLSB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
