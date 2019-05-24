package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSSCOW {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "SCZ01";
    String K_BV_CODE = "BVCD";
    String K_LOC_CCY = "344";
    char K_STS_DES = '1';
    char K_STS_NOR = '0';
    String CPN_R_ADW_SCOW = "BP-R-ADW-DMOV       ";
    String CPN_R_ADW_TLSB = "BP-R-ADW-TLSB       ";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    String WS_CODE_NO = " ";
    int WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRDMOV BPRDMOV = new BPRDMOV();
    BPCODMOV BPCODMOV = new BPCODMOV();
    BPCRDMOV BPCRDMOV = new BPCRDMOV();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRTRT BPRTRTT;
    SCCAWAC SCCAWAC;
    BPCSSCOW BPCSSCOW;
    public void MP(SCCGWA SCCGWA, BPCSSCOW BPCSSCOW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSSCOW = BPCSSCOW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSSCOW return!");
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
        IBS.init(SCCGWA, BPCRDMOV);
        IBS.init(SCCGWA, BPRDMOV);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_MAIN_SCOW_BROWSE();
        if (pgmRtn) return;
    }
    public void B010_MAIN_SCOW_BROWSE() throws IOException,SQLException,Exception {
        BPCRDMOV.FUNC = 'S';
        BPRDMOV.IN_BR = BPCSSCOW.BR;
        BPRDMOV.IN_TLR = BPCSSCOW.TLR;
        BPRDMOV.MOV_STS = BPCSSCOW.MOV_STS;
        S000_CALL_BPZRDMOV();
        if (pgmRtn) return;
        BPCRDMOV.FUNC = 'R';
        S000_CALL_BPZRDMOV();
        if (pgmRtn) return;
        B300_OUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCRDMOV.RETURN_INFO != 'N' 
            && WS_CNT <= 1000 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            BPCRDMOV.FUNC = 'R';
            S000_CALL_BPZRDMOV();
            if (pgmRtn) return;
        }
        BPCRDMOV.FUNC = 'E';
        S000_CALL_BPZRDMOV();
        if (pgmRtn) return;
    }
    public void B010_MAIN_SCOW_BROWSE_IN() throws IOException,SQLException,Exception {
        BPCRDMOV.FUNC = 'I';
        BPRDMOV.IN_BR = BPCSSCOW.IN_BR;
        BPRDMOV.IN_TLR = BPCSSCOW.TLR;
        BPRDMOV.MOV_STS = BPCSSCOW.MOV_STS;
        S000_CALL_BPZRDMOV();
        if (pgmRtn) return;
        BPCRDMOV.FUNC = 'R';
        S000_CALL_BPZRDMOV();
        if (pgmRtn) return;
        B300_OUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCRDMOV.RETURN_INFO != 'N' 
            && WS_CNT <= 1000 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            BPCRDMOV.FUNC = 'R';
            S000_CALL_BPZRDMOV();
            if (pgmRtn) return;
        }
        BPCRDMOV.FUNC = 'E';
        S000_CALL_BPZRDMOV();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCODMOV);
        CEP.TRC(SCCGWA, BPRDMOV.KEY.MOV_DT);
        CEP.TRC(SCCGWA, BPRDMOV.KEY.CONF_NO);
        CEP.TRC(SCCGWA, BPRDMOV.KEY.CODE_NO);
        CEP.TRC(SCCGWA, BPRDMOV.MOV_STS);
        CEP.TRC(SCCGWA, BPRDMOV.OUT_BR);
        CEP.TRC(SCCGWA, BPRDMOV.OUT_TLR);
        CEP.TRC(SCCGWA, BPRDMOV.IN_BR);
        CEP.TRC(SCCGWA, BPRDMOV.IN_TLR);
        CEP.TRC(SCCGWA, BPRDMOV.SC_DATE);
        CEP.TRC(SCCGWA, BPRDMOV.SC_TYPE);
        CEP.TRC(SCCGWA, BPRDMOV.SC_STS);
        CEP.TRC(SCCGWA, BPRDMOV.MC_NO);
        CEP.TRC(SCCGWA, BPCODMOV);
        BPCODMOV.MOV_DT = BPRDMOV.KEY.MOV_DT;
        BPCODMOV.CONF_NO = BPRDMOV.KEY.CONF_NO;
        BPCODMOV.CODE_NO = BPRDMOV.KEY.CODE_NO;
        BPCODMOV.MOV_STS = BPRDMOV.MOV_STS;
        BPCODMOV.OUT_BR = BPRDMOV.OUT_BR;
        BPCODMOV.OUT_TLR = BPRDMOV.OUT_TLR;
        BPCODMOV.IN_BR = BPRDMOV.IN_BR;
        BPCODMOV.IN_TLR = BPRDMOV.IN_TLR;
        BPCODMOV.SC_DATE = BPRDMOV.SC_DATE;
        BPCODMOV.SC_TYPE = BPRDMOV.SC_TYPE;
        BPCODMOV.SC_STS = BPRDMOV.SC_STS;
        BPCODMOV.MC_NO = BPRDMOV.MC_NO;
        CEP.TRC(SCCGWA, BPCODMOV.MOV_DT);
        CEP.TRC(SCCGWA, BPCODMOV.CONF_NO);
        CEP.TRC(SCCGWA, BPCODMOV.CODE_NO);
        CEP.TRC(SCCGWA, BPCODMOV.MOV_STS);
        CEP.TRC(SCCGWA, BPCODMOV.OUT_BR);
        CEP.TRC(SCCGWA, BPCODMOV.OUT_TLR);
        CEP.TRC(SCCGWA, BPCODMOV.IN_BR);
        CEP.TRC(SCCGWA, BPCODMOV.IN_TLR);
        CEP.TRC(SCCGWA, BPCODMOV.SC_DATE);
        CEP.TRC(SCCGWA, BPCODMOV.SC_TYPE);
        CEP.TRC(SCCGWA, BPCODMOV.SC_STS);
        CEP.TRC(SCCGWA, BPCODMOV.MC_NO);
        CEP.TRC(SCCGWA, BPCODMOV);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCODMOV);
        SCCMPAG.DATA_LEN = 200;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B300_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 200;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
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
    public void S000_CALL_BPZRDMOV() throws IOException,SQLException,Exception {
        BPCRDMOV.POINTER = BPRDMOV;
        BPCRDMOV.LEN = 225;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_SCOW, BPCRDMOV);
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
