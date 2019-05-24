package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSPLC {
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    DBParm CITALT_RD;
    int WS_RANDOM_NO = 0;
    char WS_VERIFIED_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRALT CIRALT = new CIRALT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSPLC CICSPLC;
    public void MP(SCCGWA SCCGWA, CICSPLC CICSPLC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSPLC = CICSPLC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIZSPLC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSPLC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (WS_VERIFIED_FLG == 'N') {
            B020_REGISTER_ALT_INF();
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        WS_VERIFIED_FLG = 'N';
        if (CICSPLC.DATA.CI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT);
        } else {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICSPLC.DATA.CI_NO;
            T000_READ_CITBAS();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_NOTFND);
            }
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            if (CIRBAS.STSW.substring(26 - 1, 26 + 2 - 1).equalsIgnoreCase("01")) {
                WS_VERIFIED_FLG = 'Y';
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_THIRD_AC_VERIFIED);
            }
        }
    }
    public void B020_REGISTER_ALT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRALT);
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "SEQ";
        BPCSGSEQ.CODE = "CIALTNO";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        CIRALT.KEY.ALT_NO = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = CIRALT.KEY.ALT_NO.length();
        for (int i=0;i<15-JIBS_tmp_int;i++) CIRALT.KEY.ALT_NO = "0" + CIRALT.KEY.ALT_NO;
        CIRALT.ENTY_TYP = '0';
        CIRALT.ENTY_NO = CICSPLC.DATA.CI_NO;
        CIRALT.ALT_TYP = "0020";
        T000_READ_CITALT_BY_CINO();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.init(SCCGWA, CIRALT);
            B021_WRITE_CITALT_INFO();
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ALT_INF_EXIST);
        }
    }
    public void B021_WRITE_CITALT_INFO() throws IOException,SQLException,Exception {
        CIRALT.KEY.ALT_NO = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = CIRALT.KEY.ALT_NO.length();
        for (int i=0;i<15-JIBS_tmp_int;i++) CIRALT.KEY.ALT_NO = "0" + CIRALT.KEY.ALT_NO;
        CIRALT.ENTY_TYP = '0';
        CIRALT.ENTY_NO = CICSPLC.DATA.CI_NO;
        CIRALT.ALT_TYP = "0020";
        CIRALT.MSG_CHNL = "TRM";
        CIRALT.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRALT.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRALT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRALT.EXP_DT = 20991231;
        CIRALT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRALT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRALT.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRALT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITALT();
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        R000_SAVE_HIS_PROC();
    }
    public void R000_SAVE_HIS_PROC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = CIRALT.ENTY_NO;
        BPCPNHIS.INFO.FMT_ID = "CIRALT";
        BPCPNHIS.INFO.FMT_ID_LEN = 268;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRALT;
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSGSEQ.RC);
        }
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITALT_BY_CINO() throws IOException,SQLException,Exception {
        CITALT_RD = new DBParm();
        CITALT_RD.TableName = "CITALT";
        CITALT_RD.eqWhere = "ENTY_TYP,ENTY_NO,ALT_TYP";
        IBS.READ(SCCGWA, CIRALT, CITALT_RD);
    }
    public void T000_WRITE_CITALT() throws IOException,SQLException,Exception {
        CITALT_RD = new DBParm();
        CITALT_RD.TableName = "CITALT";
        IBS.WRITE(SCCGWA, CIRALT, CITALT_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
