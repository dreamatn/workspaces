package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4631 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_P_MAINT_FXOG_INFO = "BP-P-MAINT-FXOG-INFO";
    String K_OUTPUT_FMT = "BP812";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_BR_TYP = " ";
    String WS_FX_BR_TYP = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFXORG BPRFXORG = new BPRFXORG();
    BPCSFXOG BPCSFXOG = new BPCSFXOG();
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
        CEP.TRC(SCCGWA, "BPOT4631 return!");
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
        B030_MOVE_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4630_AWA_4630.BR);
        CEP.TRC(SCCGWA, BPB4630_AWA_4630.FX_NO);
        CEP.TRC(SCCGWA, BPB4630_AWA_4630.INQ_TYP);
        CEP.TRC(SCCGWA, BPB4630_AWA_4630.STS);
        CEP.TRC(SCCGWA, BPB4630_AWA_4630.FX_BR);
        BPCPQORG.BR = BPB4630_AWA_4630.BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
        }
        WS_BR_TYP = BPCPQORG.TYP;
        BPCPQORG.BR = BPB4630_AWA_4630.FX_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        } else {
            if (BPCPQORG.FX_BUSI.equalsIgnoreCase("00")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_FX_ORG);
            }
        }
        WS_FX_BR_TYP = BPCPQORG.TYP;
        if (BPB4630_AWA_4630.INQ_TYP == '1') {
            if (WS_BR_TYP.equalsIgnoreCase("01") 
                || WS_BR_TYP.equalsIgnoreCase("11")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ORG_NOT_BRANCH);
            }
        } else {
            if (!WS_BR_TYP.equalsIgnoreCase("01") 
                && !WS_BR_TYP.equalsIgnoreCase("11")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ORG_MUST_BRANCH);
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFXORG);
        BPRFXORG.KEY.BR = BPB4630_AWA_4630.BR;
        BPRFXORG.KEY.FX_NO = BPB4630_AWA_4630.FX_NO;
        BPRFXORG.INQ_TYP = BPB4630_AWA_4630.INQ_TYP;
        BPRFXORG.STS = BPB4630_AWA_4630.STS;
        BPRFXORG.FX_BR = BPB4630_AWA_4630.FX_BR;
        BPCSFXOG.INFO.FUNC = 'C';
        S000_CALL_BPZSFXOG();
        if (pgmRtn) return;
    }
    public void B030_MOVE_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFXOG);
        CEP.TRC(SCCGWA, BPRFXORG.KEY.BR);
        CEP.TRC(SCCGWA, BPRFXORG.KEY.FX_NO);
        CEP.TRC(SCCGWA, BPRFXORG.INQ_TYP);
        CEP.TRC(SCCGWA, BPRFXORG.STS);
        CEP.TRC(SCCGWA, BPRFXORG.FX_BR);
        BPCOFXOG.BR = BPRFXORG.KEY.BR;
        BPCOFXOG.FX_NO = BPRFXORG.KEY.FX_NO;
        BPCOFXOG.INQ_TYP = BPRFXORG.INQ_TYP;
        BPCOFXOG.STS = BPRFXORG.STS;
        BPCOFXOG.FX_BR = BPRFXORG.FX_BR;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOFXOG;
        SCCFMT.DATA_LEN = 34;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        }
    }
    public void S000_CALL_BPZSFXOG() throws IOException,SQLException,Exception {
        BPCSFXOG.INFO.POINTER = BPRFXORG;
        BPCSFXOG.INFO.LEN = 98;
        IBS.CALLCPN(SCCGWA, CPN_P_MAINT_FXOG_INFO, BPCSFXOG);
        if (BPCSFXOG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSFXOG.RC);
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
