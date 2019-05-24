package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2090 {
    DBParm BPTTLVB_RD;
    boolean pgmRtn = false;
    String CPN_S_CASAPP_MAINTAIN = "BP-S-CASAPP-MAINTAIN";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCSARAP BPCSARAP = new BPCSARAP();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFTLCM BPCFTLCM = new BPCFTLCM();
    SCCGWA SCCGWA;
    BPB2090_AWA_2090 BPB2090_AWA_2090;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCOWA SCCOWA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2090 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2090_AWA_2090>");
        BPB2090_AWA_2090 = (BPB2090_AWA_2090) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_ARAPP_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR);
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TX_LVL == '0') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_DIRECTOR_TLR);
        }
        if (BPB2090_AWA_2090.APP_BR != ' ' 
            && BPB2090_AWA_2090.APP_BR != 0) {
            IBS.init(SCCGWA, BPCFTLCM);
            BPCFTLCM.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCFTLCM.BR = BPB2090_AWA_2090.APP_BR;
            S000_CALL_BPZFTLCM();
            if (pgmRtn) return;
            if (BPCFTLCM.AUTH_FLG == 'N') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_HAVE_DIRECT);
            }
        }
    }
    public void B020_BROWSE_ARAPP_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSARAP);
        BPCSARAP.INFO.FUNC = 'B';
        BPCSARAP.APP_NO = BPB2090_AWA_2090.APP_NO;
        BPCSARAP.APP_STS = BPB2090_AWA_2090.APP_STS;
        BPCSARAP.APP_BR = BPB2090_AWA_2090.APP_BR;
        BPCSARAP.UP_BR = BPB2090_AWA_2090.UP_BR;
        BPCSARAP.APP_DT = BPB2090_AWA_2090.APP_DT;
        CEP.TRC(SCCGWA, BPCSARAP.APP_NO);
        CEP.TRC(SCCGWA, BPCSARAP.APP_STS);
        CEP.TRC(SCCGWA, BPCSARAP.UP_BR);
        CEP.TRC(SCCGWA, BPCSARAP.APP_STS);
        CEP.TRC(SCCGWA, BPCSARAP.UP_BR);
        S000_CALL_BPZSARAP();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        BPTTLVB_RD.where = "BR = :BPRTLVB.KEY.BR "
            + "AND CRNT_TLR = :BPRTLVB.CRNT_TLR";
        IBS.READ(SCCGWA, BPRTLVB, this, BPTTLVB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR123);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLVB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSARAP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_CASAPP_MAINTAIN, BPCSARAP);
        if (BPCSARAP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSARAP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCFTLRQ.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-QRY-BR-CHK", BPCFTLCM);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
