package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4214 {
    String K_CMP_PARM_TYPE_MAINT = "BP-MAINT-PARM-TYPE";
    String K_CPN_BP_MAINT_PARM_CODE = "BP-MAINT-PARM-CODE";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSMPTY BPCSMPTY = new BPCSMPTY();
    BPCSMPCD BPCSMPCD = new BPCSMPCD();
    SCCGWA SCCGWA;
    BPB4200_AWA_4200 BPB4200_AWA_4200;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4214 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4200_AWA_4200>");
        BPB4200_AWA_4200 = (BPB4200_AWA_4200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_MAIN_PROCESS();
    }
    public void B010_QUERY_CODE_PROCESS() throws IOException,SQLException,Exception {
        if (BPB4200_AWA_4200.UP_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCSMPCD);
            BPCSMPCD.TYPE = BPB4200_AWA_4200.TYPE;
            BPCSMPCD.EFF_DATE = BPB4200_AWA_4200.EFF_DATE;
            BPCSMPCD.EXP_DATE = BPB4200_AWA_4200.EXP_DATE;
            BPCSMPCD.FUNC = 'Q';
            BPCSMPCD.OUTPUT_FLG = 'N';
            S010_CALL_BPZSMPCD();
            if (BPCSMPCD.CHECK_RESULT == 'E') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PARM_CODE_EXIST;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B100_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMPTY);
        BPCSMPTY.TYPE = BPB4200_AWA_4200.TYPE;
        BPCSMPTY.EFF_DATE = BPB4200_AWA_4200.EFF_DATE;
        BPCSMPTY.OUTPUT_FLG = 'Y';
        BPCSMPTY.FUNC = 'D';
        S000_CALL_BPZSMPTY();
    }
    public void S010_CALL_BPZSMPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CPN_BP_MAINT_PARM_CODE, BPCSMPCD);
    }
    public void S000_CALL_BPZSMPTY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_PARM_TYPE_MAINT;
        SCCCALL.COMMAREA_PTR = BPCSMPTY;
        SCCCALL.ERR_FLDNO = BPB4200_AWA_4200.FUNC_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
