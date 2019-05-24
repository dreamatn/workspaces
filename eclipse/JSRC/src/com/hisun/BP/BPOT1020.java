package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1020 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_S_INQ_FHISD = "BP-S-INQ-FHISD";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSIFHD BPCSIFHD = new BPCSIFHD();
    SCCGWA SCCGWA;
    BPB8030_AWA_8030 BPB8030_AWA_8030;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1020 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB8030_AWA_8030>");
        BPB8030_AWA_8030 = (BPB8030_AWA_8030) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_HIST();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_HIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSIFHD);
        BPCSIFHD.INPUT.JRNNO = BPB8030_AWA_8030.JRNNO;
        BPCSIFHD.INPUT.JRN_SEQ = BPB8030_AWA_8030.JRN_SEQ;
        BPCSIFHD.INPUT.AC_DT = BPB8030_AWA_8030.STR_DT;
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.JRNNO);
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.JRN_SEQ);
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.STR_DT);
        S000_CALL_BPZSIFHD();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZSIFHD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_INQ_FHISD, BPCSIFHD);
        CEP.TRC(SCCGWA, BPCSIFHD.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSIFHD.RC);
        JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, BPCSIFHD.RC);
        JIBS_tmp_str[4] = IBS.CLS2CPY(SCCGWA, BPCSIFHD.RC);
        JIBS_tmp_str[6] = IBS.CLS2CPY(SCCGWA, BPCSIFHD.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL) 
            && !JIBS_tmp_str[2].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CAN_TLR_M_IN_TXBR) 
            && !JIBS_tmp_str[4].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CAN_TLR_MUST_TXTLR) 
            && !JIBS_tmp_str[6].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CAN_TLR_MUST_TXBR)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSIFHD.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
