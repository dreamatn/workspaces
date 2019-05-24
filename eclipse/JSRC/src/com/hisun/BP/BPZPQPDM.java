package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQPDM {
    String JIBS_tmp_str[] = new String[10];
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String CPN_PARM_READ = "BP-PARM-READ        ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "PDM  INFOMATION MAINTAIN";
    String K_CPY_BPRPPDMM = "BPRPPDMM";
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPPDMM BPRPPDMM = new BPRPPDMM();
    BPRPPDMM BPROPRDM = new BPRPPDMM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCPQPDM BPCPQPDM;
    public void MP(SCCGWA SCCGWA, BPCPQPDM BPCPQPDM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQPDM = BPCPQPDM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZPQPDM return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQPDM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_PROCESS();
        B030_TRANS_DATA_OUTPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCPQPDM.PRDT_MODEL.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT, BPCPQPDM.RC);
            return;
        }
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPPDMM);
        IBS.init(SCCGWA, BPCPRMM);
        BPRPPDMM.KEY.TYP = "PRDM ";
        BPCPRMM.FUNC = '3';
        CEP.TRC(SCCGWA, BPCPQPDM.PRDT_MODEL);
        BPRPPDMM.KEY.CD = BPCPQPDM.PRDT_MODEL;
        BPCPRMM.EFF_DT = BPCPQPDM.EFF_DT;
        BPCPRMM.DAT_PTR = BPRPPDMM;
        S000_CALL_BPZPRMM();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQPDM.RC);
    }
    public void B030_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        BPCPQPDM.PRDT_MODEL = BPRPPDMM.KEY.CD;
        BPCPQPDM.EFF_DT = BPCPRMM.EFF_DT;
        BPCPQPDM.EXP_DT = BPCPRMM.EXP_DT;
        BPCPQPDM.DESC = BPRPPDMM.DATA_TXT.DESC_MODEL;
        BPCPQPDM.PARM_TX = BPRPPDMM.DATA_TXT.PARM_TX;
        BPCPQPDM.CREATE_TX = BPRPPDMM.DATA_TXT.CREATE_TX;
        BPCPQPDM.INNER_PRDT_IND = BPRPPDMM.DATA_TXT.INNER_PRDT_IND;
        BPCPQPDM.DEFT_PRDT = BPRPPDMM.DATA_TXT.DEFT_PRDT;
        BPCPQPDM.OPEN_DATE = BPRPPDMM.DATA_TXT.OPEN_DATE;
        BPCPQPDM.LAST_DATE = BPRPPDMM.DATA_TXT.LAST_DATE;
        BPCPQPDM.LAST_TLR = BPRPPDMM.DATA_TXT.LAST_TLR;
        BPCPQPDM.CTRACT_GROUP = BPRPPDMM.DATA_TXT.CONT_GROUP;
        CEP.TRC(SCCGWA, BPCPQPDM.INNER_PRDT_IND);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_READ, BPCPRMR);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_MT, BPCPRMM);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQPDM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQPDM = ");
            CEP.TRC(SCCGWA, BPCPQPDM);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
