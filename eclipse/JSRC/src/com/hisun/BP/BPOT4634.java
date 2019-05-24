package com.hisun.BP;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4634 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_P_QUERY_FXOG_INFO = "BP-P-QUERY-FXOG-INFO";
    String K_OUTPUT_FMT = "BP815";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT4634_WS_DATA WS_DATA = new BPOT4634_WS_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICQACRI CICQACRI = new CICQACRI();
    BPRFXORG BPRFXORG = new BPRFXORG();
    BPCPFXOG BPCPFXOG = new BPCPFXOG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCOFXOG BPCOFXOG = new BPCOFXOG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4630_AWA_4630 BPB4630_AWA_4630;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT4634 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4630_AWA_4630>");
        BPB4630_AWA_4630 = (BPB4630_AWA_4630) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
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
        CEP.TRC(SCCGWA, BPB4630_AWA_4630.FX_NO);
        CEP.TRC(SCCGWA, BPB4630_AWA_4630.BR);
        if (BPB4630_AWA_4630.BR != 0) {
            BPCPQORG.BR = BPB4630_AWA_4630.BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFXOG);
        BPCPFXOG.DATA_INFO.FX_NO = BPB4630_AWA_4630.FX_NO;
        BPCPFXOG.DATA_INFO.BR = BPB4630_AWA_4630.BR;
        BPCPFXOG.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCPFXOG.INFO.FUNC = 'I';
        S000_CALL_BPZPFXOG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFXOG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_FXOG_INFO, BPCPFXOG);
        if (BPCPFXOG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPFXOG.RC);
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
