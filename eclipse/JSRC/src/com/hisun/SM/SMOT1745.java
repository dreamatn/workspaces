package com.hisun.SM;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1745 {
    String JIBS_tmp_str[] = new String[10];
    String K_CMP_PARM_TYPE_MAINT = "BP-MAINT-PARM-TYPE";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSMPTY BPCSMPTY = new BPCSMPTY();
    SCCGWA SCCGWA;
    SMB1742_AWA_1742 SMB1742_AWA_1742;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "SMOT1745 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1742_AWA_1742>");
        SMB1742_AWA_1742 = (SMB1742_AWA_1742) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_MAIN_PROCESS();
    }
    public void B100_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SMB1742_AWA_1742.TYPE);
        CEP.TRC(SCCGWA, SMB1742_AWA_1742.EFF_DATE);
        IBS.init(SCCGWA, BPCSMPTY);
        BPCSMPTY.TYPE = SMB1742_AWA_1742.TYPE;
        BPCSMPTY.EFF_DATE = SMB1742_AWA_1742.EFF_DATE;
        BPCSMPTY.OUTPUT_FLG = 'Y';
        BPCSMPTY.FUNC = 'Q';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("9981745")) {
            BPCSMPTY.FUNC = 'X';
        }
        S000_CALL_BPZSMPTY();
    }
    public void S000_CALL_BPZSMPTY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_PARM_TYPE_MAINT;
        SCCCALL.COMMAREA_PTR = BPCSMPTY;
        SCCCALL.ERR_FLDNO = SMB1742_AWA_1742.FUNC_CD_NO;
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
