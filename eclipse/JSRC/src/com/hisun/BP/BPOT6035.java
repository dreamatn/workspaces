package com.hisun.BP;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6035 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PRDPR_TYPE = "PRDPR";
    String WS_CODE = " ";
    char WS_COND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPLOSS BPCPLOSS = new BPCPLOSS();
    BPCPARMC BPCPARMC = new BPCPARMC();
    BPCPRMM BPCPRMM = new BPCPRMM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB6035_AWA_6035 BPB6035_AWA_6035;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT6035 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6035_AWA_6035>");
        BPB6035_AWA_6035 = (BPB6035_AWA_6035) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
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
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB6035_AWA_6035.PARM_COD);
        CEP.TRC(SCCGWA, BPB6035_AWA_6035.EFF_DT);
        CEP.TRC(SCCGWA, BPB6035_AWA_6035.EXP_DT);
        if (WS_CODE == null) WS_CODE = "";
        JIBS_tmp_int = WS_CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) WS_CODE += " ";
        WS_CODE = "999999" + WS_CODE.substring(6);
        if (WS_CODE == null) WS_CODE = "";
        JIBS_tmp_int = WS_CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) WS_CODE += " ";
        if (BPB6035_AWA_6035.PARM_COD == null) BPB6035_AWA_6035.PARM_COD = "";
        JIBS_tmp_int = BPB6035_AWA_6035.PARM_COD.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPB6035_AWA_6035.PARM_COD += " ";
        WS_CODE = WS_CODE.substring(0, 7 - 1) + BPB6035_AWA_6035.PARM_COD + WS_CODE.substring(7 + BPB6035_AWA_6035.PARM_COD.length() - 1);
        IBS.init(SCCGWA, BPCPARMC);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPARMC.KEY.TYP = K_PRDPR_TYPE;
        BPCPRMM.FUNC = '3';
        BPCPARMC.KEY.CD = WS_CODE;
        CEP.TRC(SCCGWA, BPCPARMC.KEY.CD);
        BPCPRMM.DAT_PTR = BPCPARMC;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE == 0) {
            BPCPRMM.FUNC = '0';
            BPCPRMM.EFF_DT = BPB6035_AWA_6035.EFF_DT;
            BPCPRMM.EXP_DT = BPB6035_AWA_6035.EXP_DT;
            BPCPRMM.DAT_PTR = BPCPARMC;
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
            if (BPCPRMM.RC.RC_RTNCODE != 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_UPDATE_REC_ERR);
            }
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND);
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-PARM-MAINTAIN";
        SCCCALL.COMMAREA_PTR = BPCPRMM;
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
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
