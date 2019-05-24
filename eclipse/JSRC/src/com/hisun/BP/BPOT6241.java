package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6241 {
    String JIBS_tmp_str[] = new String[10];
    String K_FMT_CD_2 = "BP199";
    String CPN_MAINTAIN_CGWS = "BP-S-MAINTAIN-CGWS";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSCGWY BPCSCGWY = new BPCSCGWY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB6240_AWA_6240 BPB6240_AWA_6240;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6241 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6240_AWA_6240>");
        BPB6240_AWA_6240 = (BPB6240_AWA_6240) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        IBS.init(SCCGWA, BPCSCGWY);
        BPCSCGWY.FUNC = 'C';
        BPCSCGWY.PRDT_CODE = BPB6240_AWA_6240.PRDT_CD;
        BPCSCGWY.CHANG_WAY = BPB6240_AWA_6240.CHG_WAY;
        BPCSCGWY.PARM_CODE = BPB6240_AWA_6240.PARM_CD;
        S000_CALL_BPZSCGWS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB6240_AWA_6240.PRDT_CD);
        CEP.TRC(SCCGWA, BPB6240_AWA_6240.CHG_WAY);
    }
    public void S000_CALL_BPZSCGWS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_MAINTAIN_CGWS, BPCSCGWY);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCGWY.RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
