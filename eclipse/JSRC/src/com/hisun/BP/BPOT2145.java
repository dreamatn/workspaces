package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2145 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_P_MAINT_MTPA_INFO = "BP-P-MAINT-MTPA-INFO";
    String K_OUTPUT_FMT = "BP842";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPRMTPA BPRMTPA = new BPRMTPA();
    BPCSMTPA BPCSMTPA = new BPCSMTPA();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCOMTPA BPCOMTPA = new BPCOMTPA();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB2142_AWA_2142 BPB2142_AWA_2142;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2145 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2142_AWA_2142>");
        BPB2142_AWA_2142 = (BPB2142_AWA_2142) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
        B030_MOVE_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2142_AWA_2142.BR);
        CEP.TRC(SCCGWA, BPB2142_AWA_2142.APP_FLG);
        BPCPQORG.BR = BPB2142_AWA_2142.BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.RC);
        CEP.TRC(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            CEP.TRC(SCCGWA, "DEV");
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRMTPA);
        BPRMTPA.KEY.MT_BR = BPB2142_AWA_2142.BR;
        BPRMTPA.KEY.APP_FLG = BPB2142_AWA_2142.APP_FLG;
        BPCSMTPA.INFO.FUNC = 'R';
        S000_CALL_BPZSMTPA();
        if (pgmRtn) return;
        BPCSMTPA.INFO.FUNC = 'D';
        S000_CALL_BPZSMTPA();
        if (pgmRtn) return;
    }
    public void B030_MOVE_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOMTPA);
        CEP.TRC(SCCGWA, BPRMTPA.KEY.MT_BR);
        CEP.TRC(SCCGWA, BPRMTPA.KEY.APP_FLG);
        CEP.TRC(SCCGWA, BPRMTPA.MT_FLG);
        CEP.TRC(SCCGWA, BPRMTPA.MT_TLR);
        CEP.TRC(SCCGWA, BPRMTPA.MT_DT);
        CEP.TRC(SCCGWA, BPRMTPA.STAT_TM);
        CEP.TRC(SCCGWA, BPRMTPA.END_TM);
        IBS.init(SCCGWA, BPCOMTPA);
        BPCOMTPA.BR = BPRMTPA.KEY.MT_BR;
        BPCOMTPA.APP_FLG = BPRMTPA.KEY.APP_FLG;
        BPCOMTPA.MT_FLG = BPRMTPA.MT_FLG;
        BPCOMTPA.TLR = BPRMTPA.MT_TLR;
        BPCOMTPA.DT = BPRMTPA.MT_DT;
        BPCOMTPA.STAT_TM = BPRMTPA.STAT_TM;
        BPCOMTPA.END_TM = BPRMTPA.END_TM;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOMTPA;
        SCCFMT.DATA_LEN = 36;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        CEP.TRC(SCCGWA, BPCPQORG.RC);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        }
    }
    public void S000_CALL_BPZSMTPA() throws IOException,SQLException,Exception {
        BPCSMTPA.INFO.POINTER = BPRMTPA;
        BPCSMTPA.INFO.REC_LEN = 103;
        IBS.CALLCPN(SCCGWA, CPN_P_MAINT_MTPA_INFO, BPCSMTPA);
        if (BPCSMTPA.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSMTPA.RC);
        }
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
