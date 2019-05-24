package com.hisun.DD;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSAPPR {
    DBParm DDTCKNO_RD;
    String K_OUTPUT_FMT = "DD542";
    String K_HIS_COPYBOOK_NAME = "DDCSAPPR";
    String K_HIS_TX_CD = "0115350";
    String K_HIS_REMARKS = "APPROVE WRIT CONTROL";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    String WS_MSGID = " ";
    short WS_IDX = 0;
    char WS_CKNO_FLG = ' ';
    char WS_CKNO_OPEN_FLG = ' ';
    char WS_CKNO_STS = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    DDCOATAC DDCOATAC = new DDCOATAC();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DDCOAPPR DDCOAPPR = new DDCOAPPR();
    DDRCKNO DDRCKNO = new DDRCKNO();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCSAPPR DDCSAPPR;
    public void MP(SCCGWA SCCGWA, DDCSAPPR DDCSAPPR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSAPPR = DDCSAPPR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSAPPR return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_MAIN_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSAPPR.FUNC);
        CEP.TRC(SCCGWA, DDCSAPPR.AC);
        CEP.TRC(SCCGWA, DDCSAPPR.REMARK);
        if (DDCSAPPR.FUNC == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSAPPR.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.FUNC = '1';
        DCCPACTY.INPUT.AC = DDCSAPPR.AC;
        S000_CALL_DCZPACTY();
    }
    public void B020_MAIN_DATA_PROC() throws IOException,SQLException,Exception {
        if (DDCSAPPR.FUNC == '1') {
            B020_CKNO_ADD_PROC();
        } else if (DDCSAPPR.FUNC == '2') {
            B030_UPD_AGREEMENT_PROC();
        } else if (DDCSAPPR.FUNC == '3') {
            B040_DEL_AGREEMENT_PROC();
        } else if (DDCSAPPR.FUNC == '4') {
            B050_INQ_AGREEMENT_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSAPPR.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        B060_OUTPUT_DATA_PROC();
        B070_NFIN_TX_HIS_PROC();
    }
    public void B020_CKNO_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCKNO);
        DDRCKNO.KEY.AC = DDCSAPPR.AC;
        T000_READ_DDTCKNO();
        if (WS_CKNO_OPEN_FLG == 'O') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST2_REC_EXIST;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, DDRCKNO);
            DDRCKNO.KEY.AC = DDCSAPPR.AC;
            DDRCKNO.LICENSE_TYPE = DDCSAPPR.LIC_TYPE;
            DDRCKNO.LICENSE_NO = DDCSAPPR.LIC_NO;
            DDRCKNO.REMARK = DDCSAPPR.REMARK;
            DDRCKNO.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCKNO.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRCKNO.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCKNO.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_DDTCKNO();
        }
    }
    public void B030_UPD_AGREEMENT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCKNO);
        DDRCKNO.KEY.AC = DDCSAPPR.AC;
        T000_READ_UPDATE_DDTCKNO();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.init(SCCGWA, DDRCKNO);
            DDRCKNO.KEY.AC = DDCSAPPR.AC;
            DDRCKNO.LICENSE_TYPE = DDCSAPPR.LIC_TYPE;
            DDRCKNO.LICENSE_NO = DDCSAPPR.LIC_NO;
            DDRCKNO.REMARK = DDCSAPPR.REMARK;
            DDRCKNO.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCKNO.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRCKNO.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCKNO.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTCKNO();
        }
    }
    public void B040_DEL_AGREEMENT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCKNO);
        DDRCKNO.KEY.AC = DDCSAPPR.AC;
        T000_READ_UPDATE_DDTCKNO();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            T000_DELETE_DDTCKNO();
        }
    }
    public void B050_INQ_AGREEMENT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOAPPR);
        DDRCKNO.KEY.AC = DDCSAPPR.AC;
        T000_READ_DDTCKNO();
        if (WS_CKNO_OPEN_FLG == 'F') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void B060_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOAPPR);
        CEP.TRC(SCCGWA, "1111111111111111111");
        CEP.TRC(SCCGWA, DDCSAPPR.FUNC);
        CEP.TRC(SCCGWA, DDRCKNO.KEY.AC);
        CEP.TRC(SCCGWA, DDRCKNO.LICENSE_TYPE);
        CEP.TRC(SCCGWA, DDRCKNO.LICENSE_NO);
        CEP.TRC(SCCGWA, DDRCKNO.REMARK);
        DDCOAPPR.FUNC = DDCSAPPR.FUNC;
        DDCOAPPR.AC = DDRCKNO.KEY.AC;
        DDCOAPPR.LIC_TYPE = DDRCKNO.LICENSE_TYPE;
        DDCOAPPR.LIC_NO = DDRCKNO.LICENSE_NO;
        DDCOAPPR.REMARK = DDRCKNO.REMARK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOAPPR;
        SCCFMT.DATA_LEN = 229;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
    }
    public void B070_NFIN_TX_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (DDCSAPPR.FUNC == '2') {
            BPCPNHIS.INFO.TX_TYP = 'M';
        } else {
            if (DDCSAPPR.FUNC == '3') {
                BPCPNHIS.INFO.TX_TYP = 'D';
            } else {
                if (DDCSAPPR.FUNC == '4') {
                    BPCPNHIS.INFO.TX_TYP = 'O';
                } else {
                    BPCPNHIS.INFO.TX_TYP = 'A';
                }
            }
        }
        BPCPNHIS.INFO.AC = DDCSAPPR.AC;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
    }
    public void T000_READ_DDTCKNO() throws IOException,SQLException,Exception {
        WS_CKNO_OPEN_FLG = 'F';
        DDTCKNO_RD = new DBParm();
        DDTCKNO_RD.TableName = "DDTCKNO";
        DDTCKNO_RD.where = "AC = :DDRCKNO.KEY.AC";
        IBS.READ(SCCGWA, DDRCKNO, this, DDTCKNO_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CKNO_OPEN_FLG = 'O';
        }
    }
    public void T000_READ_UPDATE_DDTCKNO() throws IOException,SQLException,Exception {
        DDTCKNO_RD = new DBParm();
        DDTCKNO_RD.TableName = "DDTCKNO";
        DDTCKNO_RD.where = "AC = :DDRCKNO.KEY.AC";
        DDTCKNO_RD.upd = true;
        IBS.READ(SCCGWA, DDRCKNO, this, DDTCKNO_RD);
    }
    public void T000_WRITE_DDTCKNO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSAPPR.AC);
        DDTCKNO_RD = new DBParm();
        DDTCKNO_RD.TableName = "DDTCKNO";
        IBS.WRITE(SCCGWA, DDRCKNO, DDTCKNO_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_REWRITE_DDTCKNO() throws IOException,SQLException,Exception {
        DDTCKNO_RD = new DBParm();
        DDTCKNO_RD.TableName = "DDTCKNO";
        IBS.REWRITE(SCCGWA, DDRCKNO, DDTCKNO_RD);
    }
    public void T000_DELETE_DDTCKNO() throws IOException,SQLException,Exception {
        DDTCKNO_RD = new DBParm();
        DDTCKNO_RD.TableName = "DDTCKNO";
        IBS.DELETE(SCCGWA, DDRCKNO, DDTCKNO_RD);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
