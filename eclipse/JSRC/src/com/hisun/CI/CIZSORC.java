package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSORC {
    int JIBS_tmp_int;
    int BAS_CI_NM_LEN;
    DBParm BPTORGM_RD;
    DBParm CITBAS_RD;
    DBParm CITID_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short WS_I = 0;
    String WS_CI_NO = " ";
    String WS_RCI_NO = " ";
    String WS_ID_TYPE = " ";
    String WS_ID_NO = " ";
    String WS_CI_NM = " ";
    char WS_BAS_FOUND_FLG = ' ';
    String WS_TRT_VIL = " ";
    String WS_OPN_VIL = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRID CIRID = new CIRID();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICCINO CICCINO = new CICCINO();
    CICOSORC CICOSORC = new CICOSORC();
    BPCPARMC BPCPARMC = new BPCPARMC();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRORGM BPRORGM = new BPRORGM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSORC CICSORC;
    public void MP(SCCGWA SCCGWA, CICSORC CICSORC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSORC = CICSORC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSORC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSORC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (WS_BAS_FOUND_FLG == 'N') {
            B020_GET_CI_NO();
            if (pgmRtn) return;
            B030_WRITE_CIRBAS_INFO();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCPNHIS);
            BPCPNHIS.INFO.TX_TYP = 'A';
            R000_SAVE_HIS_PROC();
            if (pgmRtn) return;
        } else {
            R000_TRANS_DATA_TO_OUTPUT();
            if (pgmRtn) return;
        }
        B040_OUTPUT_CI_NO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSORC.DATA.CI_TYP);
        if (CICSORC.DATA.CI_TYP == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "客户类型必须是公司或同业");
        }
        CEP.TRC(SCCGWA, CICSORC.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICSORC.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICSORC.DATA.CI_NM);
        IBS.init(SCCGWA, CIRID);
        if (CICSORC.DATA.ID_TYPE.equalsIgnoreCase("23000")) {
            IBS.init(SCCGWA, CIRID);
            if (CICSORC.DATA.ID_NO == null) CICSORC.DATA.ID_NO = "";
            JIBS_tmp_int = CICSORC.DATA.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) CICSORC.DATA.ID_NO += " ";
            if (CICSORC.DATA.ID_NO == null) CICSORC.DATA.ID_NO = "";
            JIBS_tmp_int = CICSORC.DATA.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) CICSORC.DATA.ID_NO += " ";
            CIRID.ID_NO = CICSORC.DATA.ID_NO + "-" + CICSORC.DATA.ID_NO;
            if (CICSORC.DATA.ID_NO == null) CICSORC.DATA.ID_NO = "";
            JIBS_tmp_int = CICSORC.DATA.ID_NO.length();
            for (int i=0;i<70-JIBS_tmp_int;i++) CICSORC.DATA.ID_NO += " ";
            CEP.TRC(SCCGWA, CICSORC.DATA.ID_NO.substring(9 - 1, 9 + 9 - 1));
            CEP.TRC(SCCGWA, CIRID.ID_NO);
            T000_READ_CITID_BY_IDNO();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_INF_EXIST);
            }
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.ID_TYPE = CICSORC.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICSORC.DATA.ID_NO;
        CIRBAS.CI_NM = CICSORC.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        T000_READ_CITBAS_BY_IDNM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, CIRBAS.CI_ATTR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BAS_FOUND_FLG = 'N';
        } else {
            IBS.init(SCCGWA, BPRORGM);
            BPRORGM.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPTORGM_RD = new DBParm();
            BPTORGM_RD.TableName = "BPTORGM";
            IBS.READ(SCCGWA, BPRORGM, BPTORGM_RD);
            WS_TRT_VIL = BPRORGM.VIL_TYP;
            CEP.TRC(SCCGWA, WS_TRT_VIL);
            IBS.init(SCCGWA, BPRORGM);
            BPRORGM.KEY.BR = CIRBAS.OPN_BR;
            BPTORGM_RD = new DBParm();
            BPTORGM_RD.TableName = "BPTORGM";
            IBS.READ(SCCGWA, BPRORGM, BPTORGM_RD);
            WS_OPN_VIL = BPRORGM.VIL_TYP;
            CEP.TRC(SCCGWA, WS_OPN_VIL);
            if (!WS_TRT_VIL.equalsIgnoreCase(WS_OPN_VIL)) {
                WS_BAS_FOUND_FLG = 'N';
            } else {
                if (CIRBAS.CI_ATTR == '1' 
                    || CIRBAS.CI_ATTR == '3' 
                    || CIRBAS.CI_ATTR == '5') {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_EXIST);
                } else {
                    if (CIRBAS.CI_ATTR == '2' 
                        || CIRBAS.CI_ATTR == '6' 
                        || CIRBAS.CI_ATTR == '0') {
                        WS_BAS_FOUND_FLG = 'Y';
                    }
                }
            }
        }
        if (CICSORC.DATA.ID_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPARMC);
            IBS.init(SCCGWA, BPCPRMR);
            BPCPRMR.FUNC = ' ';
            BPCPRMR.TYP = "PARMC";
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            BPCPRMR.CD = "CIIDO" + BPCPRMR.CD.substring(5);
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            if (CICSORC.DATA.ID_TYPE == null) CICSORC.DATA.ID_TYPE = "";
            JIBS_tmp_int = CICSORC.DATA.ID_TYPE.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) CICSORC.DATA.ID_TYPE += " ";
            BPCPRMR.CD = BPCPRMR.CD.substring(0, 6 - 1) + CICSORC.DATA.ID_TYPE + BPCPRMR.CD.substring(6 + 5 - 1);
            BPCPRMR.DAT_PTR = BPCPARMC;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_CI_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CALL CIZCINO");
        IBS.init(SCCGWA, CICCINO);
        S000_CALL_CIZCINO();
        if (pgmRtn) return;
        WS_CI_NO = CICCINO.DATA.CI_NO;
        CEP.TRC(SCCGWA, WS_CI_NO);
    }
    public void B030_WRITE_CIRBAS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.CI_TYP = CICSORC.DATA.CI_TYP;
        CIRBAS.KEY.CI_NO = WS_CI_NO;
        CIRBAS.ID_TYPE = CICSORC.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICSORC.DATA.ID_NO;
        CIRBAS.CI_NM = CICSORC.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        CIRBAS.CI_ATTR = '0';
        CIRBAS.STSW = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        CIRBAS.STSW = "100" + CIRBAS.STSW.substring(3);
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        CIRBAS.STSW = CIRBAS.STSW.substring(0, 24 - 1) + "1" + CIRBAS.STSW.substring(24 + 1 - 1);
        CIRBAS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRBAS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITBAS();
        if (pgmRtn) return;
        R000_TRANS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
    }
    public void B040_OUTPUT_CI_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICOSORC);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIO02";
        SCCFMT.DATA_PTR = CICOSORC;
        SCCFMT.DATA_LEN = 423;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOSORC);
        CICOSORC.DATA.CI_TYP = CIRBAS.CI_TYP;
        CICOSORC.DATA.CI_NO = CIRBAS.KEY.CI_NO;
        CICOSORC.DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICOSORC.DATA.ID_NO = CIRBAS.ID_NO;
        if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
        JIBS_tmp_int = CIRBAS.CI_NM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
        CICOSORC.DATA.CI_NM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        CICOSORC.DATA.CI_ATTR = CIRBAS.CI_ATTR;
        CICOSORC.DATA.CI_STSW = CIRBAS.STSW;
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
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
    }
    public void S000_CALL_CIZCINO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-CI-NO", CICCINO);
        if (CICCINO.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCINO.RC);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NOT_CFM_ID_TYPE);
        }
    }
    public void T000_READ_CITBAS_BY_IDNM() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "ID_TYPE,ID_NO,CI_NM";
        CITBAS_RD.where = "SUBSTR ( STSW , 3 , 1 ) = '0' "
            + "AND SUBSTR ( STSW , 23 , 1 ) = '0'";
        IBS.READ(SCCGWA, CIRBAS, this, CITBAS_RD);
    }
    public void T000_WRITE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.WRITE(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITID_BY_IDNO() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        CITID_RD.where = "ID_TYPE = '20001' "
            + "AND ID_NO = :CIRID.ID_NO";
        IBS.READ(SCCGWA, CIRID, this, CITID_RD);
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
