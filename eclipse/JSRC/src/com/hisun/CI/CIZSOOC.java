package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSOOC {
    int BAS_CI_NM_LEN;
    DBParm BPTORGM_RD;
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_TRT_VIL = " ";
    String WS_OPN_VIL = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRBAS CIRBASN = new CIRBAS();
    CICCINO CICCINO = new CICCINO();
    CICOCINO CICOCINO = new CICOCINO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRORGM BPRORGM = new BPRORGM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSOOC CICSOOC;
    public void MP(SCCGWA SCCGWA, CICSOOC CICSOOC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSOOC = CICSOOC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSOOC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSOOC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_OPEN_ONCE_CUST();
        if (pgmRtn) return;
        B100_WRITE_HISTORY();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSOOC.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICSOOC.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICSOOC.DATA.CI_NM);
        if (CICSOOC.DATA.ID_TYPE.trim().length() == 0 
            || CICSOOC.DATA.ID_NO.trim().length() == 0 
            || CICSOOC.DATA.CI_NM.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "三要素必须输�?");
        }
    }
    public void B020_OPEN_ONCE_CUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.ID_TYPE = CICSOOC.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICSOOC.DATA.ID_NO;
        CIRBAS.CI_NM = CICSOOC.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        T000_READ_CITBAS_BY_IDNM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
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
            if (WS_TRT_VIL.equalsIgnoreCase(WS_OPN_VIL)) {
                CEP.TRC(SCCGWA, "BAS INF FOUND");
                R000_OUTPUT_DATA();
                if (pgmRtn) return;
                Z_RET();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, CICCINO);
        S000_CALL_CIZCINO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCINO.DATA.CI_NO);
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICCINO.DATA.CI_NO;
        CIRBAS.CI_TYP = '1';
        CIRBAS.CI_ATTR = '4';
        CIRBAS.STSW = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        CIRBAS.STSW = "1" + CIRBAS.STSW.substring(1);
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        CIRBAS.STSW = CIRBAS.STSW.substring(0, 24 - 1) + "1" + CIRBAS.STSW.substring(24 + 1 - 1);
        CIRBAS.CI_NM = CICSOOC.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        CIRBAS.ID_TYPE = CICSOOC.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICSOOC.DATA.ID_NO;
        CIRBAS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRBAS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITBAS();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASN);
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B100_WRITE_HISTORY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = CIRBAS.KEY.CI_NO;
        BPCPNHIS.INFO.FMT_ID = "CIRBAS";
        BPCPNHIS.INFO.FMT_ID_LEN = 568;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRBASN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
    } //FROM #ENDIF
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOCINO);
        CICOCINO.DATA.CI_NO = CIRBAS.KEY.CI_NO;
        CICOCINO.DATA.CI_TYP = CIRBAS.CI_TYP;
        CICOCINO.DATA.CI_ATTR = CIRBAS.CI_ATTR;
        CICOCINO.DATA.CI_STSW = CIRBAS.STSW;
        if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
        JIBS_tmp_int = CIRBAS.VER_STSW.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
        if (CIRBAS.VER_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CICOCINO.DATA.VER_FLG = 'T';
        } else {
            CICOCINO.DATA.VER_FLG = 'F';
        }
        CICOCINO.DATA.IDE_STSW = CIRBAS.IDE_STSW;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIO01";
        SCCFMT.DATA_PTR = CICOCINO;
        SCCFMT.DATA_LEN = 125;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZCINO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-CI-NO", CICCINO);
        if (CICCINO.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCINO.RC);
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
