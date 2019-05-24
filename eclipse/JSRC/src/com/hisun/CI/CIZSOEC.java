package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.util.Random;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSOEC {
    int JIBS_tmp_int;
    Random random;
    String JIBS_tmp_str[] = new String[10];
    int BAS_CI_NM_LEN;
    DBParm CITBAS_RD;
    DBParm CITRELN_RD;
    char WS_SPE_CINO_OPEN_FLG = ' ';
    int WS_RANDOM_NO = 0;
    String WS_CI_NO = " ";
    char WS_CI_TYP = ' ';
    String WS_CI_NM = " ";
    String WS_ID_NO = " ";
    String WS_EC_ID_NO = " ";
    String WS_ID_TYPE = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRRELN CIRRELN = new CIRRELN();
    CICCINO CICCINO = new CICCINO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSOEC CICSOEC;
    public void MP(SCCGWA SCCGWA, CICSOEC CICSOEC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSOEC = CICSOEC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIZSOEC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSOEC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (WS_SPE_CINO_OPEN_FLG == 'N' 
            && CICSOEC.DATA.READ_ONLY_FLG != 'Y') {
            B020_GET_CI_NO();
            B030_GET_ID_NO();
            B040_WRITE_CITBAS_INFO();
            B050_WRITE_CITRELN_INFO();
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        WS_SPE_CINO_OPEN_FLG = 'N';
        if (CICSOEC.DATA.CI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        } else {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICSOEC.DATA.CI_NO;
            T000_READ_CITBAS();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_NOTFND);
            }
            WS_CI_TYP = CIRBAS.CI_TYP;
            WS_ID_TYPE = CIRBAS.ID_TYPE;
            WS_ID_NO = CIRBAS.ID_NO;
            WS_CI_NM = CIRBAS.CI_NM;
        }
        if (CICSOEC.DATA.SPECIAL_CI_NO.trim().length() > 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NO_INPUT_ERR, "特殊客户号不可输�?");
        }
        IBS.init(SCCGWA, CIRRELN);
        CIRRELN.RCI_NO = CICSOEC.DATA.CI_NO;
        CIRRELN.KEY.CIREL_CD = "59999";
        T000_READ_CIRRELN_BY_RCIRELCD();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (CIRRELN.RCI_STS == '0') {
                IBS.init(SCCGWA, CIRBAS);
                CIRBAS.KEY.CI_NO = CIRRELN.KEY.CI_NO;
                T000_READ_CITBAS();
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_NOTFND, "特殊客户基本信息不存�?");
                }
                WS_SPE_CINO_OPEN_FLG = 'Y';
                CICSOEC.DATA.SPECIAL_CI_NO = CIRRELN.KEY.CI_NO;
            } else {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RELN_STS_ABNORMAL);
            }
        }
    }
    public void B020_GET_CI_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCINO);
        S000_CALL_CIZCINO();
        WS_CI_NO = CICCINO.DATA.CI_NO;
        CICSOEC.DATA.SPECIAL_CI_NO = WS_CI_NO;
        CEP.TRC(SCCGWA, WS_CI_NO);
    }
    public void B030_GET_ID_NO() throws IOException,SQLException,Exception {
        if (WS_CI_NO == null) WS_CI_NO = "";
        JIBS_tmp_int = WS_CI_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_CI_NO += " ";
        if (WS_EC_ID_NO == null) WS_EC_ID_NO = "";
        JIBS_tmp_int = WS_EC_ID_NO.length();
        for (int i=0;i<70-JIBS_tmp_int;i++) WS_EC_ID_NO += " ";
        WS_EC_ID_NO = WS_CI_NO.substring(7 - 1, 7 + 3 - 1) + WS_EC_ID_NO.substring(3);
        if (WS_CI_NO == null) WS_CI_NO = "";
        JIBS_tmp_int = WS_CI_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_CI_NO += " ";
        if (WS_EC_ID_NO == null) WS_EC_ID_NO = "";
        JIBS_tmp_int = WS_EC_ID_NO.length();
        for (int i=0;i<70-JIBS_tmp_int;i++) WS_EC_ID_NO += " ";
        WS_EC_ID_NO = WS_EC_ID_NO.substring(0, 4 - 1) + WS_CI_NO.substring(4 - 1, 4 + 3 - 1) + WS_EC_ID_NO.substring(4 + 3 - 1);
        if (WS_CI_NO == null) WS_CI_NO = "";
        JIBS_tmp_int = WS_CI_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_CI_NO += " ";
        if (WS_EC_ID_NO == null) WS_EC_ID_NO = "";
        JIBS_tmp_int = WS_EC_ID_NO.length();
        for (int i=0;i<70-JIBS_tmp_int;i++) WS_EC_ID_NO += " ";
        WS_EC_ID_NO = WS_EC_ID_NO.substring(0, 7 - 1) + WS_CI_NO.substring(10 - 1, 10 + 2 - 1) + WS_EC_ID_NO.substring(7 + 3 - 1);
        if (WS_CI_NO == null) WS_CI_NO = "";
        JIBS_tmp_int = WS_CI_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_CI_NO += " ";
        if (WS_EC_ID_NO == null) WS_EC_ID_NO = "";
        JIBS_tmp_int = WS_EC_ID_NO.length();
        for (int i=0;i<70-JIBS_tmp_int;i++) WS_EC_ID_NO += " ";
        WS_EC_ID_NO = WS_EC_ID_NO.substring(0, 9 - 1) + WS_CI_NO.substring(0, 3) + WS_EC_ID_NO.substring(9 + 3 - 1);
        if (WS_CI_NO == null) WS_CI_NO = "";
        JIBS_tmp_int = WS_CI_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_CI_NO += " ";
        if (WS_EC_ID_NO == null) WS_EC_ID_NO = "";
        JIBS_tmp_int = WS_EC_ID_NO.length();
        for (int i=0;i<70-JIBS_tmp_int;i++) WS_EC_ID_NO += " ";
        WS_EC_ID_NO = WS_EC_ID_NO.substring(0, 12 - 1) + WS_CI_NO.substring(12 - 1, 12 + 1 - 1) + WS_EC_ID_NO.substring(12 + 1 - 1);
        WS_RANDOM_NO = 0;
        WS_RANDOM_NO = 999999;
        random = new Random();
        WS_RANDOM_NO = random.nextInt(WS_RANDOM_NO);
        CEP.TRC(SCCGWA, WS_RANDOM_NO);
        if (WS_EC_ID_NO == null) WS_EC_ID_NO = "";
        JIBS_tmp_int = WS_EC_ID_NO.length();
        for (int i=0;i<70-JIBS_tmp_int;i++) WS_EC_ID_NO += " ";
        JIBS_tmp_str[0] = "" + WS_RANDOM_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_EC_ID_NO = WS_EC_ID_NO.substring(0, 13 - 1) + JIBS_tmp_str[0] + WS_EC_ID_NO.substring(13 + 6 - 1);
        CEP.TRC(SCCGWA, WS_EC_ID_NO);
    }
    public void B040_WRITE_CITBAS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = WS_CI_NO;
        CIRBAS.CI_TYP = WS_CI_TYP;
        CIRBAS.CI_ATTR = '5';
        if (WS_CI_TYP == '1') {
            CIRBAS.ID_TYPE = "19100";
        } else {
            CIRBAS.ID_TYPE = "29100";
        }
        CIRBAS.ID_NO = WS_EC_ID_NO;
        CIRBAS.CI_NM = "南商（中国）保证�?";
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        CIRBAS.STSW = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        CIRBAS.STSW = "100" + CIRBAS.STSW.substring(3);
        CIRBAS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRBAS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITBAS();
    }
    public void B050_WRITE_CITRELN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRRELN);
        CIRRELN.RCI_NO = CICSOEC.DATA.CI_NO;
        CIRRELN.KEY.CI_NO = WS_CI_NO;
        CIRRELN.KEY.CIREL_CD = "59999";
        CIRRELN.RCI_STS = '0';
        CIRRELN.KEY.RCI_IDTYP = WS_ID_TYPE;
        CIRRELN.KEY.RCI_IDNO = WS_ID_NO;
        CIRRELN.KEY.RCI_NAME = WS_CI_NM;
        CIRRELN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRRELN.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRRELN.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRRELN.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRRELN.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRRELN.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITRELN();
    }
    public void R000_SAVE_HIS_PROC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = CIRBAS.KEY.CI_NO;
        BPCPNHIS.INFO.FMT_ID = "CIRBAS";
        BPCPNHIS.INFO.FMT_ID_LEN = 568;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRBAS;
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void S000_CALL_CIZCINO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-CI-NO", CICCINO);
        if (CICCINO.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCINO.RC);
        }
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CIRRELN_BY_RCIRELCD() throws IOException,SQLException,Exception {
        CITRELN_RD = new DBParm();
        CITRELN_RD.TableName = "CITRELN";
        CITRELN_RD.eqWhere = "RCI_NO , CIREL_CD";
        IBS.READ(SCCGWA, CIRRELN, CITRELN_RD);
    }
    public void T000_WRITE_CITRELN() throws IOException,SQLException,Exception {
        CITRELN_RD = new DBParm();
        CITRELN_RD.TableName = "CITRELN";
        IBS.WRITE(SCCGWA, CIRRELN, CITRELN_RD);
    }
    public void T000_WRITE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.WRITE(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
